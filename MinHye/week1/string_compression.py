def count(stack):
    res = ''
    # print(stack)
    if len(stack) == 0:
        return res
    
    for i in stack:
        cnt, word = i[0], i[1]
        if cnt == 1:
            res += word
        else:
            res += str(cnt)
            res += word
    
    return res

def solution(s):
    res = []   
    if len(s) == 1:
        return 1
    for i in range(1, int(len(s)/2)+1):
        stack = []    
        word = s[0:i]
        cnt = 1
        stack.append([cnt, word])
        
        j = i
        while True:
            if j+i > len(s):
                new_ = s[j:]
            else:
                new_ = s[j:j+i]
  
            if word == new_: 
                stack[-1][0] += 1
                j += i
            else:
                word = new_
                cnt = 1
                stack.append([cnt, word])
                j += i
                
            if j >= len(s):
                break
            
        if len(res) == 0:
            res = stack
        elif len(count(res)) > len(count(stack)): 
            res = stack        
        
    
    return len(count(res))
 
# s = "aabbaccc" #7
# s = "ababcdcdababcdcd" #9
# s = "abcabcdede" #8
# s = "abcabcabcabcdededededede" #14
# s = "xababcdcdababcdcd" #17
s = 'a'
print(solution(s))

