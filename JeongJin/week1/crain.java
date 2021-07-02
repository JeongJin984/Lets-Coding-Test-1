import java.util.*;

class Solution {
    
    public int solution(int[][] board, int[] moves) {
        int height = board.length;
        int width = board[0].length;
        
        int result = 0;
        
        int top[] = new int[board.length];
        Stack<Integer> stack = new Stack<>();
        
        for(int j=0; j<width; j++) {
            int i=0;
            while(board[i][j] == 0) {
                i++;
            }
            top[j] = i;
        }
        
        
        for(int p : moves) {
            int next = p-1;
            
            if(top[next] == height) continue;
            else {
                int doll = board[top[next]][next];
                if(stack.isEmpty()) stack.push(doll);
                else {
                    if(stack.peek() == doll) {
                        stack.pop();
                        result += 2;
                    } else {
                        stack.push(doll);
                    }
                }
                top[next]++;
            }
        }
        
        return result;
    }
}