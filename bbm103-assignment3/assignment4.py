import sys
maze_txt = sys.argv[1]
maze_health_txt = sys.argv[2]
hp = int(sys.argv[3])
output_txt = sys.argv[4]
health = hp
maze1 = open(maze_txt, 'r')
maze2 = open(maze_health_txt, 'r')

maze_list1 = [[i for i in line.strip('\n')] for line in maze1]
maze_list2 = [[i for i in line.strip('\n')] for line in maze2]
maze_list3 = maze_list2[:]


def length_finder(list_name):
    return len(list_name[0])


length1 = length_finder(maze_list1)
length2 = length_finder(maze_list2)
length3 = length_finder(maze_list3)


def write_board(maze_list):
    for line in maze_list:
        print(*line, sep=', ', file=output)
    output.write('\n')


def fix_array(maze_list):
    for p in range(len(maze_list)):
        for t in range(length_finder(maze_list)):
            if maze_list[p][t] == 'W' or maze_list[p][t] == 'P':
                maze_list[p][t] = '0'


def index_finder(maze_list, obj):
    global y, x
    for element in maze_list:
        for item in element:
            if item == obj:
                y = maze_list.index(element)
                x = element.index(obj)


def maze_solver(a, b, m, length, goal):
    global found, health
    moves = [0, 0, 0, 0]
    if m == maze_list2 and health <= 0:
        return maze_health()

    if m[a][b] is goal:
        found = 1
        fix_array(m)
        write_board(m)
        return
    if found is 1:
        return
    if a - 1 >= 0:
        if m[a - 1][b] is 'H':
            moves[0] = 3
        if m[a - 1][b] is 'F':
            moves[0] = 2
        if m[a - 1][b] is 'P':
            moves[0] = 1
    if b - 1 >= 0:
        if m[a][b - 1] is 'H':
            moves[1] = 3
        if m[a][b - 1] is 'F':
            moves[1] = 2
        if m[a][b - 1] is 'P':
            moves[1] = 1
    if a + 1 < length:
        if m[a + 1][b] is 'H':
            moves[2] = 3
        if m[a + 1][b] is 'F':
            moves[2] = 2
        if m[a + 1][b] is 'P':
            moves[2] = 1
    if b + 1 < length:
        if m[a][b + 1] is 'H':
            moves[3] = 3
        if m[a][b + 1] is 'F':
            moves[3] = 2
        if m[a][b + 1] is 'P':
            moves[3] = 1

    if moves[0] is 1:
        m[a - 1][b] = '1'
        health -= 1
        maze_solver(a - 1, b, m, length, goal)
        if found == 0:
            m[a - 1][b] = 'P'

    if found is 1:
        return

    if moves[1] is 1:
        health -= 1
        m[a][b - 1] = '1'
        maze_solver(a, b - 1, m, length, goal)
        if found == 0:
            m[a][b - 1] = 'P'

    if found is 1:
        return

    if moves[2] is 1:
        health -= 1
        m[a + 1][b] = '1'
        maze_solver(a + 1, b, m, length, goal)
        if found == 0:
            m[a + 1][b] = 'P'

    if found is 1:
        return

    if moves[3] is 1:
        health -= 1
        m[a][b + 1] = '1'
        maze_solver(a, b + 1, m, length, goal)
        if found == 0:
            m[a][b + 1] = 'P'

    if found is 1:
        return

    if moves[0] is 2:
        maze_solver(a - 1, b, m, length, goal)
        return
    if moves[1] is 2:
        maze_solver(a, b - 1, m, length, goal)
        return
    if moves[2] is 2:
        maze_solver(a + 1, b, m, length, goal)
        return
    if moves[3] is 2:
        maze_solver(a, b + 1, m, length, goal)
        return

    if moves[0] is 3:
        m[a - 1][b] = '1'
        health = hp
        maze_solver(a - 1, b, m, length, goal)
        if found == 0:
            m[a - 1][b] = 'H'

    if found is 1:
        return

    if moves[1] is 3:
        m[a][b - 1] = '1'
        health = hp
        maze_solver(a, b - 1, m, length, goal)
        if found == 0:
            m[a][b - 1] = 'H'

    if found is 1:
        return

    if moves[2] is 3:
        m[a + 1][b] = '1'
        health = hp
        maze_solver(a + 1, b, m, length, goal)
        if found == 0:
            m[a + 1][b] = 'H'

    if found is 1:
        return

    if moves[3] is 3:
        m[a][b + 1] = '1'
        health = hp
        maze_solver(a, b + 1, m, length, goal)
        if found == 0:
            m[a][b + 1] = 'H'

    if found is 1:
        return


def maze_health():
    global y, x, health
    health = hp
    index_finder(maze_list3, 'S')
    maze_solver(y, x, maze_list3, length3, 'H')


health = hp
found = 0
output = open(output_txt, 'w')
index_finder(maze_list1, 'S')
maze_solver(y, x, maze_list1, length1, 'F')

health = hp
found = 0
index_finder(maze_list2, 'S')
maze_solver(y, x, maze_list2, length2, 'F')

