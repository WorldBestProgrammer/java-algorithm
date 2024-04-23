import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        int[][][] size = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                size[i][i][j] = matrix[i][j];
            }
        }

        if (N == 1) {
            return;
        }
        for (int i = 0; i < N - 1; i++) {
            int m = i;
            int n = i+1;
            dp[m][n] = size[m][m][0] * size[m][m][1] * size[n][n][1];
            size[m][n][0] = size[m][m][0];
            size[m][n][1] = size[n][n][1];
        }
        for (int step = 2; step < N; step++) {
            for (int i = 0; i < N - step; i++) {
                int m = i;
                int n = i + step;
                for (int j = 0; j < n - m; j++) {
                    dp[m][n] = Math.min(dp[m][n], dp[m][m + j] + dp[m + j + 1][n] + size[m][m + j][0] * size[m][m + j][1] * size[m + j +1][n][1]);
                }
                size[m][n][0] = size[m][n-1][0];
                size[m][n][1] = size[n][n][1];
            }
        }

        System.out.println(dp[0][N-1]);
    }
}
