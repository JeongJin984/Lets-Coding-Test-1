from collections import deque

def solution(board, moves):
    answer = 0
    basket = [-1]
    
    board_ = list(map(list, zip(*board)))
    board_ = [deque([row_el for row_el in row if row_el != 0]) for row in board_]
    
        
    for move in moves:
        if len(board_[move-1]) != 0:
            pop = board_[move-1].popleft()
        else:
            continue
        
        if basket[-1] == -1:
            basket.append(pop)
        elif basket[-1] == 0:
            continue
        elif basket[-1] == pop:
            basket.pop()
            answer += 2
        else:
            basket.append(pop)
    
    return answer

board = [[0,0,0,0,0],[0,0,11,0,13],[0,12,15,0,11],[14,12,14,14,12],[13,15,11,13,11]]
moves = [1,5,3,5,1,2,1,4]

print(solution(board, moves))