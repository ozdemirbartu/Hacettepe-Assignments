import sys
input_File = sys.argv[1]
output_File = sys.argv[2]
y = open(output_File, 'w+')
x = open(input_File, 'r')

list_a = [line.strip('\n').split('\t') for line in x]  # creating list from input file
x.close()  # closing the input file
dic_a = {}  # creating an empty list
for element in list_a:  # for every element in list_a
    dic_a.setdefault(element[0], []).append(element[1:])  # appending values in list format to keys


for key in sorted(dic_a):
    index_of_key = list(sorted(dic_a.keys())).index(key)
    y.write('Message {}'.format(index_of_key+1) + '\n')
    for value in sorted(dic_a[key]):
        y.write(key + ' ' + str(value[0]) + ' ' + str(value[1]) + '\n')
y.close()
