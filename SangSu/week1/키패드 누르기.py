def getDistance(start, end):
    offset = 0 if start == 1 or start == 4 or start == 7 or start == 10 else 1
    
    return abs(start // 3 - end // 3) + offset

def solution(numbers, hand):
    answer = ''
    
    left  = 9
    right = 11
    
    for number in numbers:
        number -= 1
        
        if number == 0 or number == 3 or number == 6:
            answer += 'L'
            left    = number
        elif number == 2 or number == 5 or number == 8:
            answer += 'R'
            right   = number
        else:
            if number == -1:
                number = 10
            
            leftDistance  = getDistance(left, number)
            rightDistance = getDistance(right, number)
            
            if leftDistance < rightDistance:
                answer += 'L'
                left    = number
            elif leftDistance > rightDistance:
                answer += 'R'
                right   = number
            else:
                if hand == 'left':
                    answer += 'L'
                    left    = number
                else:
                    answer += 'R'
                    right   = number
    
    return answer