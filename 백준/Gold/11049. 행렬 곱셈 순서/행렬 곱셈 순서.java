import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        for (int step = 1; step < N; step++) {
            for (int i = 0; i < N - step; i++) {
                int m = i;
                int n = i + step;
                dp[m][n] = Integer.MAX_VALUE;
                for (int j = 0; j < n - m; j++) {
                    dp[m][n] = Math.min(dp[m][n],
                            dp[m][m + j] + dp[m + j + 1][n] + matrix[m][0] * matrix[m + j][1]
                                    * matrix[n][1]);
                }
            }
        }

        System.out.println(dp[0][N - 1]);
    }
}
