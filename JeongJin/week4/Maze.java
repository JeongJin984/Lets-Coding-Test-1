package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    final int INF = 0x7f7f7f7f;
    int[][] D = new int[1004][1024];
    List<Vertex>[] adj = new ArrayList[1004];
    List<Vertex>[] revAdj = new ArrayList[1004];
    int[] trapIdx = new int[1004];

    boolean bitmask(int state, int idx) {
        int x = 1 << trapIdx[idx];  // 2의 trapIdx[idx]승 즉 10000... 이렇게 나옴 이게 비트마스크로 표현된 state
        return (x & state) != 0;    // 논리곱
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        for(int i=1; i<=n; i++) {
            for(int j=0; j<1024; j++) {
                D[i][j] = INF;
            }
        }
        for(int i=1; i<=n; i++) {
            adj[i] = new ArrayList<>();
            revAdj[i] = new ArrayList<>();
        }
        for(int i=1; i<=n; i++) trapIdx[i] = -1;
        for(int i=0; i< traps.length; i++) trapIdx[traps[i]] = i;

        for(int[] road : roads) {
            int startV = road[0];
            int endV = road[1];
            int weight = road[2];
            adj[startV].add(new Vertex(endV, weight));
            revAdj[endV].add(new Vertex(startV, weight));
        }

        D[start][0] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, D[start][0], 0)); // 아무 트랩도 안밟아서 0인 상태

        /*여기까지가 초기화 부분 */

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();   // 도착해서 밟은 거임

            if(cur.idx == end) return cur.weight;   // 도착했으니 pq는 항상 최소 weight를 먼저 방문하므로 바로 반환
            if(D[cur.idx][cur.state] != cur.weight) {
                continue;
            }

            for(Vertex next : adj[cur.idx]) {       // 정간선 Check
                if(isTrapped(cur, next)) continue;   // Trapped 는 밟아서 역간선이 됬다는 뜻
                updateDArrayAndAddToPQ(cur, next, pq);
            }

            for(Vertex next : revAdj[cur.idx]) {    // 역간선 Check
                if(!isTrapped(cur, next)) continue; // Trapped false 는 안밟거나 두번 발동되서 정간선이 됬다는 뜻
                updateDArrayAndAddToPQ(cur, next, pq);
            }
        }
        return -1;
    }

    boolean isTrapped(Edge cur, Vertex next) {
        int rev = 0;
        if(trapIdx[cur.idx] != -1 && bitmask(cur.state, cur.idx)) rev ^= 1; // 뒤집음
        if(trapIdx[next.idx]!= -1 && bitmask(cur.state, next.idx)) rev ^= 1; // 뒤집음
        // 위 의 두 조건 중 하나만 만족하면 뒤집힌 상태라는 뜻이므로 현재 Vertex는 역간선에서 체크 해야함
        // 즉 이부분이 현재 Vertex가 정간선 상태인지 역간선 상태인지 판별하는 부분
        // rev 0 이면 정간선 1이면 역간선
        return rev != 0;
    }

    void updateDArrayAndAddToPQ(Edge cur, Vertex next, PriorityQueue<Edge> pq) {
        int next_state = cur.state;
        if(trapIdx[next.idx] != -1) next_state ^= (1 << trapIdx[next.idx]); // 다음상태 = 현재상태(비트마스크) + 비트마스크로 표현된 추가할 상태
        if(D[next.idx][next_state] > next.weight + cur.weight) {
            D[next.idx][next_state] = next.weight + cur.weight;
            pq.add(new Edge(next.idx, D[next.idx][next_state], next_state));
        }
    }

    static class Edge implements Comparable<Edge> {
        int idx, weight, state;

        public Edge(int idx, int weight, int state) {
            this.idx = idx;
            this.weight = weight;
            this.state = state;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static class Vertex {
        int idx, weight;

        public Vertex(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
}
