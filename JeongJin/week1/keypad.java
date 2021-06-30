package week1;
class Solution {
    
    int[][] position = {
        {3, 1},
        {0, 0},
        {0, 1},
        {0, 2},
        {1, 0},
        {1, 1},
        {1, 2},
        {2, 0},
        {2, 1},
        {2, 2},
        {3, 0},
        {3, 2}
    };
    
    public int getDistance(int n1, int n2) {
        return Math.abs(position[n1][0] - position[n2][0]) 
            + Math.abs(position[n1][1] - position[n2][1]);
    }
    
    public String getNext(int cur, int leftP, int rightP, String hand) {
        
        if(cur == 1 || cur == 4 || cur == 7) return "L";
        else if(cur == 3 || cur == 6 || cur == 9) return "R";
        
        int leftD = getDistance(cur, leftP);
        int rightD = getDistance(cur, rightP);
        
        if(leftD == rightD) {
            return hand.equals("left") ? "L" : "R";
        } else {
            return leftD < rightD ? "L" : "R";
        }
    }
    
    public String solution(int[] numbers, String hand) {
        String answer = "";
        
        int leftP = 10;
        int rightP = 11;
        for(int number : numbers) {
            String next = getNext(number, leftP, rightP, hand);
            if(next.equals("L")) leftP = number;
            else rightP = number;
            answer += next;
        }
        return answer;
    }
}