class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        
        if(s.length() == 1) return 1;

        for(int l=1; l <= (s.length() / 2); l++) {
            int length = s.length();
            for(int i=l; i<=s.length()-l;) {
                String standard = s.substring(i-l, i);
                i += l;
                String now = s.substring(i-l, i);

                boolean isSame = false;
                int num = 0;
                while(standard.equals(now)) {
                    isSame = true;
                    i += l;
                    num++;
                    if(i > s.length()) break;
                    now = s.substring(i-l, i);
                }

                if(isSame) length = length - (num * l) + Integer.toString(num+1).length();
            }
            answer = Math.min(answer, length);
        }
        return answer;
    }
}
