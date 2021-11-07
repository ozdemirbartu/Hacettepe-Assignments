import sys
l = sys.argv[1].split(',') # Split string
l = [int(i) for i in l] # Transform string pieces into integers
nl = []  # list that stores unwanted elements of l
n = 0  # step


def Diff(list1, list2):
    """Takes l and removes unwanted elements of l and add step +1"""
    global l
    global n
    l = sorted(list(set(list1) - set(list2)))
    n += 1
for i in l :
    if i < 0:
        l.remove(i)


if l[n] == 1:  # If number we pick is 1 do this loop
    for i in l[1::2]:
        nl.append(i)
    Diff(l, nl)

while l[n] < len(l):  # Main loop do until number is less than length of l
    nl = l[l[n] - 1::l[n]]  # List of unwanted elements
    Diff(l, nl)
    if l[n] > len(l):
        break
print(*l, sep="  ")
