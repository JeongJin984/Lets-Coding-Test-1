import java.util.*;

class Solution {

    static class Rate {
        double rate;
        int stage;

        public Rate(double rate, int stage) {
            this.rate = rate;
            this.stage = stage;
        }
    }

    public int[] solution(int N, int[] stages) {
        Rate[] rateMap = new Rate[N];
        int[] answer = new int[N];

        int[] cleared = new int[N+1];
        int[] reached = new int[N+2];

        for(int s : stages) {
            for(int i=1; i<s; i++) {
                cleared[i]++;
                reached[i]++;
            }
            reached[s]++;
        }

        for(int i=1; i<=N; i++) {
            double rate = 0;
            if(reached[i] == 0) {
                rate = 0;
            } else {
                rate = (double)(reached[i] - cleared[i]) / reached[i];
            }
            rateMap[i-1] = new Rate(rate, i);
        }

        Arrays.sort(rateMap, new Comparator<Rate>() {
            @Override
            public int compare(Rate r1, Rate r2) {
                if(r1.rate < r2.rate) return 1;
                else if(r1.rate == r2.rate) {
                    if(r1.stage > r2.stage) return 1;
                    else if(r1.stage == r2.stage) return 0;
                    else return -1;
                }
                else return -1;
            }
        });

        for(int i=0; i<rateMap.length; i++) {
            answer[i] = rateMap[i].stage;
        }

        return answer;
    }
}