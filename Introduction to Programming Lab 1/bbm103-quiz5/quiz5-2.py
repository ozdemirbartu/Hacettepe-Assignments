import sys
a = int(sys.argv[1])
b = [i for i in range(a-1, -1, -1)] + [i for i in range(1, a)]
d = [j for j in range(1, 2 * a, 2)] + [j for j in range((2*a-3),0,-2)]
diamond = map(lambda i, j: i*' '+j*'*' + i*' ', b, d)

for i in diamond:
    print(i)
