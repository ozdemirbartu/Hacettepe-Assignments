import sys
a = int(sys.argv[1])
b = int(sys.argv[2])
c = a**b

print(str(a)+'^'+str(b), end=' = ')
while c > 10:
    sums = 0
    print(c, '= '+' + '.join(str(c)), end=' = ')
    for i in str(c):
        sums += int(i)
        c = sums
print(c)
