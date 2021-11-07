import time
import sys

if len(sys.argv) != 3:  # if user inputs number of arguments other than 2 argument print error and exit
    print('You must write two arguments for this program')
    sys.exit(1)

first_file = sys.argv[1]
second_file = sys.argv[2]

correct_words = open(first_file, 'r', encoding='utf-8')
letter_values = open(second_file, 'r', encoding='utf-8')


def turkish_lower(x):
    """Helps converting 'İ' and 'I' in Turkish forms"""
    return x.replace('I', 'ı').replace('İ', 'i').lower()


dic_correct_words = {}  # empty dictionary for correct_words
for line in correct_words:
    (key, value) = line.split(':')  # by splitting each line with ':' we get keys and values but in a string
    dic_correct_words[turkish_lower(key)] = turkish_lower(value).strip('\n').split(',')  # we change strings to values

dic_letter_values = {}  # empty dictionary for letter_values
for line in letter_values:
    (key, value) = line.split(':')  # by splitting each line with ':' we get keys and values but in string form
    dic_letter_values[turkish_lower(key)] = int(value.strip('\n'))  # we change value string to integers


def counter():
    """main timer and time printer"""
    global time_left
    end_time = time.time()  # what's the time now
    elapsed_time = end_time - initial_time  # elapsed time since initial time
    time_left = int(30 - elapsed_time)  # how many seconds left
    if time_left <= 0:  # print this if player has not time
        print('You have 0 time')
    if time_left > 0:
        print('You have {} time'.format(time_left))  # print how many seconds player has


def word_checker():
    """checks if word is correct and not added in found_words"""
    global found_words
    if time_left > 0:
        if guess in dic_correct_words[shuffled_word]:  # if guess is in values of the shuffled word
            if guess not in found_words:  # if guess is not in the found_words add it to found_words
                found_words.append(guess)
            elif guess in found_words:  # if guess is in the found_words print error
                print('This word is guessed before')
        else:
            print('your guessed word is not a valid word')
    else:
        pass


def user_input():
    """function that does main input loop"""
    global found_words, guess
    guess = turkish_lower(input('Guessed Word: '))  # make input all lowercase
    counter()  # get time_left and print it
    word_checker()


def point_calculator():
    """Point calculator for every shuffled word and also for total points"""
    global word_total, section_total, overall_total
    section_total = 0  # sum points of a shuffled word
    for word in found_words:  # for a correctly guessed word
        word_total = 0  # let word point 0
        for letter in word:
            word_total += dic_letter_values[letter]  # get letter values(points) for that specific word and sum
        word_total = word_total*len(word)  # final point for that specific word
        section_total += word_total  # final section total for the shuffled word
    overall_total += section_total  # final sum of all shuffled words
    return section_total


overall_total = 0

for shuffled_word in dic_correct_words:  # get new shuffled word and iterate over dictionary
    time_left = 30  # set the timer
    initial_time = time.time()  # start timer (also reset it before a new shuffled word)
    found_words = []  # empty list for correct guessed words
    print('Shuffled letters are:   {} '.format(shuffled_word), end="  ")  # print shuffled word
    print('Please guess words for these letters with minimum three letters')

    while time_left > 0:  # when player still have time
        user_input()  # let player guess
    print('Score for {} is {}'.format(shuffled_word, point_calculator()), end=' ')  # if player has no time print score
    if len(found_words) > 0:  # if player guessed at least 1 word correctly
        print('and guessed words are: {}'.format('-'.join(found_words)).lower())  # print the correctly guessed words
    else:
        print("you couldn't guess any word right")  # if player couldn't guess any correct word print this

print('Your total score is {}!'.format(overall_total))
