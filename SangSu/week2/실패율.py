def solution(N, stages):
    answer = []
    
    challengers = [0] * (N + 1)
    failure     = [0] * N
    
    for stage in stages:
        challengers[stage - 1] += 1
    
    challengerNumber = challengers[N]
    
    for index in range(N - 1, -1, -1):
        challengerNumber += challengers[index]
        
        if challengerNumber > 0:
            failure[index] = challengers[index] / challengerNumber
    
    answer = sorted(range(1, len(failure) + 1), key=lambda index: failure[index - 1], reverse=True)
    
    return answer