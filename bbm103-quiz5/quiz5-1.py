import sys
a = int(sys.argv[1])
b = a
asterisk_count = 1


def print_dia(b):
    """It prints first half of the diamond, then calls the second function when it's done"""
    global asterisk_count  # it holds the number of asterisk for a particular line (default=1)
    if b == 0:  # if a becomes zero (caused by 10th line)
        return print2_dia(b)  # call the second function
    space_count = b - 1  # number of spaces is one less than input
    print(space_count * ' ' + asterisk_count * '*' + space_count * ' ')  # print the line
    asterisk_count = asterisk_count + 2  # for each line we proceed, number of asterisks will increase by 2
    print_dia(b-1)  # call same function but with a-1 for the next print line


def print2_dia(b):
    """It prints second half of the diamond"""
    global asterisk_count
    if b == a:  # if a equals to first input (caused by 22th line)
        return  # stop
    asterisk_count = asterisk_count - 2  # each time we call this function decrease the number of asterisks by 2
    space_count = b + 1  # each time we call this function increase space count by 1
    print(space_count * ' ' + (asterisk_count - 2) * '*' + space_count * ' ')
    print2_dia(b+1)  # call same function but with a+1 for the next print line


print_dia(b)
