import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    static int size;
    static int caseNum;
    static int[] fileSize;

    static int[] sum;

    static int[][] dp;

    static int getSum(int i, int j) {
        if(i==0) return sum[j];
        return sum[j] - sum[i-1];
    }

    static int solution() {
        for(int j=1; j<size; j++) {
            for(int i=j-1; i>=0; i--) {
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=i; k<j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
                }
                dp[i][j] += getSum(i, j);
            }
        }

        return dp[0][size-1];
    }

    static void init(BufferedReader br) throws IOException {
        size = Integer.parseInt(br.readLine());

        dp = new int[size][size];
        sum = new int[size];

        fileSize = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        sum[0] = fileSize[0];
        for(int i=1; i<size; i++) {
            sum[i] = sum[i-1] + fileSize[i];
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        caseNum = Integer.parseInt(br.readLine());

        while(caseNum-- > 0) {
            init(br);

            int result = solution();

            System.out.println(result);
        }

    }

}
