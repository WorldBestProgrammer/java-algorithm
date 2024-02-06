import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] whitePaper = new int[201][201];
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            paintWhitePaper(whitePaper, row * 2, col * 2);
        }

        int sum = 0;
        for (int i = 0; i < 201; i++) {
            for (int j = 0; j < 201; j++) {
                if (whitePaper[i][j] == 1) {
                    if (i+1 < 201 && j + 1 < 201
                            && whitePaper[i+1][j] == 1 && whitePaper[i][j+1] == 1 && whitePaper[i+1][j+1] == 1) {
                        sum += 1;
                    }
                }
            }
        }
        System.out.println(sum / 4);
    }

    private static void paintWhitePaper(int[][] whitePaper, int row, int col) {
        for (int i = row; i <= row + 20; i++) {
            for (int j = col; j <= col + 20; j++) {
                whitePaper[i][j] = 1;
            }
        }
    }
}
