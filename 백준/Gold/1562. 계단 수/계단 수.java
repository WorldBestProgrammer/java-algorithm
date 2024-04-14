import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 2^10 = 1024 1111111111 2^10 - 1
        int[][][] dp = new int[N+1][10][1 << 10];
        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        int mod = 1_000_000_000;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    int nk = (1 << j) | k;
                    if (j == 0) {
                        dp[i][j][nk] += dp[i-1][1][k] % mod;
                    } else if (j == 9) {
                        dp[i][j][nk] += dp[i-1][8][k] % mod;
                    } else {
                        dp[i][j][nk] += dp[i - 1][j - 1][k] % mod + dp[i - 1][j + 1][k] % mod;
                    }
                    dp[i][j][nk] %= mod;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[N][i][(1 << 10) - 1] % mod;
            sum %= mod;
        }

        System.out.println(sum);
    }
}
