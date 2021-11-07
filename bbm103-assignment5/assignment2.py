b = []
board = []
n = int(input('What Size Game GoPy3?'))
while n < 3:  # ask for a valid size
    n = int(input('Enter a valid size: '))

for i in range(n ** 2):  # one-line list of the board
    b.append(i)
for j in range(0, n ** 2, n):  # list 'c' is list 'b' but in 2D
    board.append(b[j:j + n])


def h_win(player_sign):
    """ Check the rows for win condition"""
    global win
    for z in board:
        if z == list(player_sign * n):
            print('Winner:', player_sign)
            win = True
        else:
            pass
    return win


def v_win(player_sign):
    """Check columns for win condition"""
    global win
    for d in range(n):
        v_list = []
        for y in range(n):
            v_list.append(board[y][d])
            if v_list == list(player_sign * n):
                print('Winner:', player_sign)
                win = True
            else:
                pass
    return win


def d_win(player_sign):
    """Checks diagonal lines for win condition"""
    global win
    a = n
    d1_list = []
    d2_list = []
    for u in range(n):  # for loop for first diagonal line
        d1_list.append(board[u][u])
        if d1_list == list(player_sign * n):  # if lists are equal then it's a win!
            print('Winner:', player_sign)
            win = True
        else:
            pass
    for t in range(a):  # for loop for second diagonal line
        d2_list.append(board[t][a - 1])
        a -= 1
        if d2_list == list(player_sign * n):  # if lists are equal then it's a win!
            print('Winner:', player_sign)
            win = True
        else:
            pass
    return win


def refresh_board(player):
    """subsitutes a position with player input then prints the new board(right aligned)"""
    board[int(player_input // n)][int(player_input % n)] = player
    for k in board:  # now print list
        alligned_board = n * '{:>3}'
        print(alligned_board.format(*k))


def show_board():
    """"just shows the current status of the board(right aligned)"""
    for k in board:  # now print list
        alligned_board = n * '{:>3}'
        print(alligned_board.format(*k))


show_board()
win = False
while not win:
    # Player 1 Turn
    player_input = int(input('Player_1 turn--> '))
    if n**2 >player_input >= 0:   # check the move to make sure if it is in the range
        coordinate = board[int(player_input // n)][int(player_input % n)]
        if coordinate == 'X':
            print('You have made this choice before')
            show_board()
        elif coordinate == 'O':
            print('The other player select this cell before.')
            show_board()
        else:
            refresh_board('X')
            if h_win('X') or v_win('X') or d_win('X'):
                break
    else:
        print('Your input is not in the range.')

    # Player 2 Turn
    player_input = int(input('Player_2 turn--> '))
    if n**2 >player_input >= 0:
        coordinate = board[int(player_input // n)][int(player_input % n)]
        if coordinate == 'O':
            print('You have made this choice before')
            show_board()
        elif coordinate == 'X':
            print('The other player select this cell before.')
            show_board()
        else:
            refresh_board('O')
            if h_win('O') or v_win('O') or d_win('O'):
                break
            else:
                pass
    else:
        print('Your input is not in the range.')
