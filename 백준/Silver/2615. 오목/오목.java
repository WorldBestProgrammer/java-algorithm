import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
//		for (int i = 0; i < 19; i++) {
//			for (int j = 0; j < 19; j++) {
//				System.out.println("1");
//			}
//		}
//		System.setIn(new FileInputStream("Test5.txt"));
		//여기에 코드를 작성하세요.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		
		int[][] table = new int[19][19];
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 19; j++) {
				int token = Integer.parseInt(st.nextToken());
				table[i][j] = token;
			}
		}
		
		int[] dx = {1, 0, -1, 1};
		int[] dy = {1, 1, 1, 0};

		out: for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (table[i][j] == 0) {
					continue;
				}
				for (int k = 0; k < 4; k++) {
					if (i - dx[k] >=0 && i - dx[k] < 19 && j - dy[k] >=0 && j - dy[k] < 19 && table[i-dx[k]][j-dy[k]] == table[i][j]) {
						continue;
					}
					boolean flag = check(table, i, j, dx[k], dy[k]);
					if (flag) {
						System.out.println(table[i][j]);
						System.out.println((i + 1) + " " + (j + 1));
						
						return;
					}
				}
			}
		}
		System.out.println(0);
		
	}
	
	private static boolean check(int[][] table, int i, int j, int dx, int dy) {
		int value = table[i][j];
		int count = 1;
		int ni = i;
		int nj = j;
		for (int k = 0; k < 4; k++) {
			ni += dx;
			nj += dy;
			if (ni >=0 && ni < 19 && nj >=0 && nj < 19 && table[ni][nj] == value) {
				count += 1;
			}
		}
//		ni = i;
//		nj = j;
//		for (int k = 0; k < 4; k++) {
//			ni -= dx;
//			nj -= dy;
//			if (ni >=0 && ni < 19 && nj >=0 && nj < 19 && table[ni][nj] == value) {
//				count += 1;
//			}
//		}
		
		if (count == 5) {
//			return true;
			ni += dx;
			nj += dy;
			if (ni >=0 && ni < 19 && nj >=0 && nj < 19 && table[ni][nj] == value) {
				return false;
			} else {
				return true; 
			}
		}
		return false;
	}

}

