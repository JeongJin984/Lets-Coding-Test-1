T = int(input())

for _ in range(T):
    K         = int(input())
    fileSizes = list(map(int, input().split()))
    
    table = [[-1] * K for _ in range(K)]
    
    for index in range(K):
        table[index][index] = (fileSizes[index], 0)
    
    for index in range(1, K):
        for row in range(K):
            if row + index >= K:
                break
            
            for offset in range(index, 0, -1):
                x = table[row][row + index - offset]
                y = table[row + index - offset + 1][row + index]
                
                cost      = x[0] + y[0]
                totalCost = x[1] + y[1] + cost
                
                if table[row][row + index] == -1 or table[row][row + index][1] > totalCost:
                    table[row][row + index] = (cost, totalCost)
    
    print(table[0][K - 1][1])