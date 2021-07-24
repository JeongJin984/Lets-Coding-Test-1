class Solution {
    public int solution(String s) {

        String[] replace = new String[]{
                "zero",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine",
        };

        String answer = s;
        for(int i=0; i<replace.length; i++) {
            String from = replace[i];
            String to = "" + i;
            answer = answer.replaceAll(from, to);
        }

        return Integer.parseInt(answer);
    }
}
