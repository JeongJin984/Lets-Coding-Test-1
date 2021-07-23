package com.company;

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] wall = new int[size + 1][size + 1];
        int[][][] dp = new int[size + 1][size + 1][3];
        for (int x = 1; x <= size; x++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int y = 1; y <= size; y++) {
                wall[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        dp[1][2][0] = 1;
        for (int x = 1; x <= size; x++) {
            for (int y = 1; y <= size; y++) {
                if(wall[x][y]==1) continue;
                dp[x][y][0] += dp[x][y - 1][0] + dp[x][y - 1][2];
                dp[x][y][1] += dp[x - 1][y][1] + dp[x - 1][y][2];
                if (wall[x - 1][y] == 0 && wall[x][y - 1] == 0) {
                    dp[x][y][2] += dp[x - 1][y - 1][0] + dp[x - 1][y - 1][1] + dp[x - 1][y - 1][2];
                }
            }
        }
        System.out.println(dp[size][size][0] + dp[size][size][1] + dp[size][size][2]);
        br.close();
    }
}

// bfs로 풀다가 시간초과남
// bfs가 dp보다 빠른 이유: bfs는 나가는 조건을 체크해야 하고 전에 저장했던 것을 사용하지 못한다.
// 하지만 dp는 loop동안 사용햇던 정보를 활용하여 다음 정보를 얻을 수 있다.
