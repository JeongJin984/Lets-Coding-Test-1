N = int(input())

house = []
table = [[] * N for _ in range(N)]

for index in range(N):
    house.append(list(map(int, input().split())))
    
    for _ in range(N):
        table[index].append({
            'horizontal': 0,
            'vertical': 0,
            'diagonal': 0
        })

table[0][1]['horizontal'] = 1

for col in range(2, N):
    if house[0][col] == 0:
        table[0][col]['horizontal'] = table[0][col - 1]['horizontal']

for row in range(1, N):
    for col in range(1, N):
        if house[row][col] == 1:
            continue
        
        table[row][col]['horizontal'] = table[row][col - 1]['horizontal'] + table[row][col - 1]['diagonal']
        table[row][col]['vertical']   = table[row - 1][col]['vertical'] + table[row - 1][col]['diagonal']
        
        if house[row][col - 1] == 0 and house[row - 1][col] == 0 and house[row - 1][col - 1] == 0:
            table[row][col]['diagonal'] = table[row - 1][col - 1]['horizontal'] + table[row - 1][col - 1]['vertical'] + table[row - 1][col - 1]['diagonal']

print(sum(table[N - 1][N - 1].values()))