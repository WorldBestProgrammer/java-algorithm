import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] array;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] countries = new int[6][3];
        out: for (int k = 0; k < 4; k++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                int count = 0;
                for (int j = 0; j < 3; j++) {
                    countries[i][j] = Integer.parseInt(st.nextToken());
                    count += countries[i][j];
                }
                if (count != 5) {
                    sb.append(0).append(" ");
                    continue  out;
                }
            }
//            System.out.println("k" + k);
            int[][] result = new int[6][6];
            if (dfs(0, countries, result)) {
                sb.append(1).append(" ");
            } else {
                sb.append(0).append(" ");
            }

        }
        System.out.println(sb);
    }

    private static boolean dfs(int i, int[][] countries, int[][] result) {
//        System.out.println(i);
        if (i == 5) {
            return true;
        }

        array = new int[5 - i];
        int x = 0;

        int win = countries[i][0];
        int tie = countries[i][1];
        int lose = countries[i][2];

        for (int j = 0; j < i; j++) {
            int r = result[i][j];
            if (r == -1) {
                lose -= 1;
            } else if (r == 0) {
                tie -= 1;
            } else {
                win -= 1;
            }
        }

        for (int j = 0; j < lose; j++) {
            array[x++] = -1;
        }
        for (int j = 0; j < tie; j++) {
            array[x++] = 0;
        }
        for (int j = 0; j < win; j++) {
            array[x++] = 1;
        }


        do {
//            System.out.println(Arrays.toString(array));
            int[] temp = Arrays.copyOf(array, array.length);
//			System.out.println(Arrays.toString(array));
            for (int j = i+1; j < 6; j++) {
                result[i][j] = array[j-(i+1)];
                result[j][i] = 0 - result[i][j];
            }

            int winCount = 0;
            int tieCount = 0;
            int loseCount = 0;
            for (int j = 0; j < i + 1; j++) {
                if (result[i+1][j] == 1) {
                    winCount += 1;
                } else if (result[i+1][j] == 0) {
                    tieCount += 1;
                } else if (result[i+1][j] == -1) {
                    loseCount += 1;
                }
            }
            if (!(winCount <= countries[i+1][0] && tieCount <= countries[i+1][1]
                    && loseCount <= countries[i+1][2])) {
                continue;
            }
            if(dfs(i+1, countries, result)) {
                return true;
            }
            array = temp;

        } while(np());

        return false;

    }
    private static boolean np() {

        int i = array.length - 1;
        while (i > 0 && array[i-1] >= array[i]) {
            i--;
        }
        if (i == 0) {
            return false;
        }
        int j = array.length - 1;
        while (array[i-1] >= array[j]) {
            j--;
        }

        int temp = array[j];
        array[j] = array[i-1];
        array[i-1] = temp;

        Arrays.sort(array, i, array.length);
        return true;
    }
}