import sys

a, b, c = int(sys.argv[1]), int(sys.argv[2]), int(sys.argv[3])
delta = b**2 - 4*a*c
root_1 = (-b+delta**(1/2)) / (2*a)
root_2 = (-b-delta**(1/2)) / (2*a)

if delta > 0:
    print("There are two solutions\nSolution(s): {0:.2f} {1:.2f}".format(root_1, root_2))
elif delta == 0:
    print("There is one solution\nSolution(s): {0:.2f}".format(root_1))
else:
    print("There is no real solution")
    
