class Solution {
    final int maxSize = 5;

    boolean isIn(int[] next) {
        return next[0] >= 0 && next[1] >= 0 && next[0] < maxSize && next[1] < maxSize;
    }

    private boolean isValidPosition(String[] place, int[] cur, int[] next, int type) {
        if(place[next[0]].charAt(next[1]) == 'P') {
            if(type == 1) {
                if(place[cur[0]].charAt(next[1]) == 'X' && place[next[0]].charAt(cur[1]) == 'X') {
                    return true;
                } else {
                    return false;
                }
            } else if(type == 2) {
                if(place[(cur[0] + next[0])/2].charAt(cur[1]) == 'X') {
                    return true;
                } else {
                    return  false;
                }
            } else {
                if(place[cur[0]].charAt((cur[1] + next[1])/2) == 'X') {
                    return true;
                } else {
                    return  false;
                }
            }
        } else {
            return true;
        }
    }

    public int getDistance(int[] cur, int[] next) {
        return Math.abs(cur[0]-next[0]) + Math.abs(cur[1]-next[1]);
    }

    private int isPValid(String[] place) {
        for(int i=0; i<place.length; i++) {
            for(int j=0; j<place.length; j++) {
                char v = place[i].charAt(j);
                int[] cur = new int[]{i, j};
                if(v=='P') {
                    for(int ri=-2; ri<=2; ri++) {
                        int t = ri < 0 ? -ri : ri;
                        for(int rj = 2 - t; rj>=t-2; rj--) {
                            int[] next = new int[]{i + ri, j + rj};
                            if(isIn(next)) {
                                int d = getDistance(cur, next);
                                if(d == 1 && place[next[0]].charAt(next[1]) == 'P') {
                                    return 0;
                                } else if(d == 2) {
                                    if(!isValidPosition(place,cur, next, t)) {
                                        return 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return 1;
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for(int i=0; i<answer.length; i++){
            String[] place = places[i];
            int result = isPValid(place);
            answer[i] = result;
        }

        return answer;
    }
}
