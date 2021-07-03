def solution(numbers, hand):
    answer = ''
    l , r = 10, 12
    
    for n in numbers:
        if n == 0:
            n = 11
        if n % 3 == 1:
            l = n
            answer += 'L'
        elif n % 3 == 0:
            r = n
            answer += 'R'
        else:
            l_ = (l-1) // 3 + 1
            r_ = (r-1) // 3 + 1
            n_ = (n-1) // 3 + 1
            
            ld = abs(l_ - n_) + 1
            rd = abs(r_ - n_) + 1
            
            if l % 3 == 2: ld -= 1
            if r % 3 == 2: rd -= 1
            
            if ld > rd:
                r = n
                answer += 'R'
            elif ld < rd:
                l = n
                answer += 'L'
            else:
                if hand == 'left' : 
                    l = n
                    answer += 'L'
                else: 
                    r = n
                    answer += 'R'
    return answer

# numbers = [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]
numbers = [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]
hand ='left'
# hand ='right'
print(solution(numbers, hand))
