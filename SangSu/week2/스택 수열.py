n = int(input())

number = 0
stack  = []

answer = ''

for index in range(n):
    x = int(input())
    
    while x > number:
        number += 1
        stack.append(number)
        
        answer += '+\n'
    
    while len(stack) > 0 and x < stack[-1]:
        stack.pop()
        
        answer += '-\n'
    
    if len(stack) == 0 or x != stack[-1]:
        answer = 'NO'
        
        break
    else:
        stack.pop()
        
        answer += '-\n'

print(answer)