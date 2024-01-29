import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    /**
     * 최대, 최소 문제이므로 그리디, DP, 탐색
     * 항상 최선의 선택을 할 수는 없으므로 그리디 X
     * 중간 결과를 이용할 방법이 없으므로 DP X
     * 따라서 탐색인데 완전 탐색을 하기에는 경우의 수가 너무 많다. 256^n이다.
     */


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] A = new int[N][M];
        int[][] B = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                A[i][j] = charArray[j] - '0';
            }
        }
        for (int i = 0; i < N; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                B[i][j] = charArray[j] - '0';
            }
        }

        int count = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                if (validateChange(A, B, i, j)) {
                    flip(A, i, j);
                    count++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] != B[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(count);
    }

    private static void flip(int[][] A, int x, int y) {
        int[] dx = {0, 0, 0, 1, 1, 1, 2, 2, 2};
        int[] dy = {0, 1, 2, 0, 1, 2, 0, 1, 2};

        for (int i = 0; i < 9; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            A[nx][ny] = Math.abs(1 - A[nx][ny]);
        }
    }

    private static boolean validateChange(int[][] A, int[][] B, int x, int y) {
        return A[x][y] != B[x][y];
    }
}