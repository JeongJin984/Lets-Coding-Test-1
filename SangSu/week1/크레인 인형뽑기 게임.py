def solution(board, moves):
    answer = 0
    
    boardSize       = len(board)
    transposedBoard = [[] for _ in range(boardSize)]
    
    stack = []
    
    for x in range(boardSize):
        for y in range(boardSize - 1, -1, -1):
            if board[y][x] == 0:
                break
            
            transposedBoard[x].append(board[y][x])
    
    for move in moves:
        move -= 1
        
        if len(transposedBoard[move]) > 0:
            doll = transposedBoard[move].pop()
            
            if len(stack) > 0 and doll == stack[-1]:
                stack.pop()
                answer += 2
            else:
                stack.append(doll)
    
    return answer