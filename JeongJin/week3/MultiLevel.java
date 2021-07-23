import java.util.*;

class Solution {
    Map<String, String> parents = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String ,Integer> answerMap = new HashMap<>();

        for(int i=0; i<amount.length; i++) {
            amount[i] *= 100;
        }

        parents.put("-", null);
        for(int i=0; i<enroll.length; i++) {
            parents.put(enroll[i], referral[i]);
        }

        for(int i=0; i<seller.length; i++) {
            int revenue = amount[i];
            int share = (int)revenue;

            String cur = seller[i];
            while(!cur.equals("-") && share > 0) {
                revenue = share;
                share = Math.round(revenue/10);
                revenue = revenue - share;
                answerMap.put(cur, answerMap.getOrDefault(cur, 0) + (int)revenue);
                cur = parents.get(cur);
            }
        }

        int[] answer = new int[enroll.length];
        for(int i=0; i<enroll.length; i++) {
            answer[i] = answerMap.getOrDefault(enroll[i], 0);
        }
        return answer;
    }
}
