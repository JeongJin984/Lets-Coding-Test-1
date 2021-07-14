from collections import deque

N = int(input())
K = int(input())

apples = {}

for index in range(K):
    row, col = map(int, input().split())
    
    apples[(col - 1, row - 1)] = True

L = int(input())

movements = {}

for index in range(L):
    X, C = input().split()
    
    movements[int(X)] = C

time = 0

snake        = deque([(0, 0)])
direction    = 1
headPosition = [0, 0]

while True:
    time += 1
    
    if direction == 0:
        headPosition[1] -= 1
    elif direction == 1:
        headPosition[0] += 1
    elif direction == 2:
        headPosition[1] += 1
    else:
        headPosition[0] -= 1
    
    if headPosition[0] < 0 or headPosition[0] >= N or headPosition[1] < 0 or headPosition[1] >= N:
        break
    
    headPositionTuple = tuple(headPosition)
    
    if headPositionTuple in apples:
        snake.appendleft(headPositionTuple)
        
        del apples[headPositionTuple]
    else:
        collision = False
        
        snake.append(headPositionTuple)
        
        for index in range(len(snake) - 2):
            position = snake.popleft()
            
            if headPositionTuple == position:
                collision = True
                
                break
            
            snake.append(position)
        
        if collision == True:
            break
        
        position = snake.popleft()
        
        if headPositionTuple == position:
            break
    
    if time in movements:
        if movements[time] == 'L':
            direction = (direction - 1) % 4
        else:
            direction = (direction + 1) % 4

print(time)