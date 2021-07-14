N, K = map(int, input().split())

numbers = list(range(1, N + 1))
index   = K - 1

print('<', end='')

while len(numbers) > 1:
    print(f'{numbers[index]}, ', end='')
    
    del numbers[index]
    
    index = (index + K - 1) % len(numbers)

print(f'{numbers[0]}>')