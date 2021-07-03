def solution(s):
    stringLength = len(s)
    
    answer = stringLength
    
    for unit in range(1, stringLength):
        compressedString = ''
        
        lastString       = ''
        lastStringNumber = 1
        
        for start in range(0, stringLength, unit):
            string = s[start:start + unit]
            
            if string == lastString:
                lastStringNumber += 1
            else:
                if lastStringNumber > 1:
                    compressedString += str(lastStringNumber)
                
                compressedString += lastString
                
                lastString       = string
                lastStringNumber = 1
        
        if lastStringNumber > 1:
            compressedString += str(lastStringNumber)
        
        compressedString += lastString
        
        if len(compressedString) < answer:
            answer = len(compressedString)
    
    return answer