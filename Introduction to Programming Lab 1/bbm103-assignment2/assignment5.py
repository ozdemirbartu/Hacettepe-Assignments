import numpy as np
import pandas as pd
import matplotlib.pyplot as plt


def read_and_divide_into_train_and_test(csv_file):
    global training_inputs, test_inputs, data, test_labels, training_labels
    data = pd.read_csv(csv_file)
    data.drop(columns='Code_number', inplace=True)
    ints = []
    for i in data['Bare_Nuclei']:
        if i != '?':
            ints.append(int(i))
    median = np.median(sorted(ints))
    data.replace(to_replace='?', value=int(median), inplace=True)
    data = data.astype(int)
    a = int(data.shape[0]*0.8)
    training_inputs = data.iloc[:a]
    training_labels = training_inputs['Class']
    test_inputs = data.iloc[a:]
    test_labels = test_inputs['Class']
    return training_inputs, test_inputs, data, test_labels, training_labels


def create_heat_map():
    global numpy_corr, numpy_corr_show
    training_columns = [i for i in corr]
    numpy_corr = np.array(corr)
    numpy_corr_show = np.around(numpy_corr, decimals=2)
    fig, ax = plt.subplots(figsize=(8, 8))

    im = ax.imshow(numpy_corr, cmap='viridis')

    ax.set_xticks(np.arange(len(training_columns)))
    ax.set_yticks(np.arange(len(training_columns)))

    ax.set_xticklabels(training_columns)
    ax.set_yticklabels(training_columns)

    plt.setp(ax.get_xticklabels(), rotation=45, ha="right",
             rotation_mode="anchor")

    for i in range(len(training_columns)):
        for j in range(len(training_columns)):
            ax.text(j, i, numpy_corr_show[i, j], ha="center", va="center", color="k")

    plt.colorbar(im, fraction=0.046, pad=0.04)
    ax.set_title("Pairwise correlations of the attributes of training set")
    fig.tight_layout()
    plt.show()


read_and_divide_into_train_and_test("breast-cancer-wisconsin.csv")

training_inputs = training_inputs.copy()
training_inputs.drop(columns='Class', inplace=True)
np_training_in = np.array(training_inputs)

np_training_labels = np.array(training_labels)
np_training_labels = np_training_labels.reshape(len(np_training_labels), 1)


test_inputs = test_inputs.copy()
test_inputs.drop(columns='Class', inplace=True)
np_test_in = np.array(test_inputs)

corr = training_inputs.corr(method='pearson')
create_heat_map()


np_test_labels = np.array(test_labels)
np_test_labels = np_test_labels.reshape(len(np_test_labels), 1)


def sigmoid(x):
    return 1 / (1 + np.exp(-0.005*x))


def sigmoid_derivative(x):
    return 0.005 * x * (1 - x)


def run(inputs, labels):
    global weights, output
    for i in range(train_times):
        ins = inputs
        output = sigmoid(np.dot(ins, weights))
        error = labels - output
        error_array.append(np.mean(error))
        adjustments = np.dot(ins.T, error * sigmoid_derivative(output))
        weights += adjustments
        accuracy_array.append(test_and_accuracy())


def test_and_accuracy():
    test_predictions = np.rint(sigmoid(np.dot(np_test_in, weights))).astype(int)
    tp = 0
    for predicted_val, label in zip(test_predictions, np_test_labels):
        if predicted_val == label:
            tp += 1
    accuracy = tp / len(np_test_labels)
    return accuracy


train_times = 2500
np.random.seed(1)
weights = 2 * np.random.random((9, 1)) - 1
accuracy_array = []
error_array = []

run(np_training_in, np_training_labels)

plt.plot(accuracy_array, color='red')
plt.title('Accuracy Rate', color='red')
plt.xlabel('Epochs')
plt.ylabel('Accuracy')
plt.yticks(np.arange(min(accuracy_array), 1, 0.1))
plt.show()

plt.plot(error_array)
plt.title('Error Rate', color='blue')
plt.xlabel('Epochs')
plt.ylabel('Loss')
plt.show()
