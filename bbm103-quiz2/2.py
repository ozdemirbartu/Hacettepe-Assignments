import sys
S = sys.argv[1]
s_str = S.split(",")                                            #split input and assign it as a new list

s_int = [int(i) for i in s_str if int(i) > 0]                   #transform strings in s_str into integers and copy them in s_int

E = [i for i in s_int if i % 2 == 0]                            #find even numbers in s_int copy them in E

print("Even Numbers: " + '\"' + ','.join(map(str, E)) + '\"')   # str + "even1,even2..."
print("Sum of Even Numbers:", sum(E))
print("Even Number Rate:", round(sum(E) / sum(s_int), 3))
