import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] numbers = new int[M];
		StringBuilder sb = new StringBuilder();
		displayCombination(sb, numbers, N, M, 0, 1);
		
		bw.write(sb.toString());
		bw.close();
	}
	
	private static void displayCombination(StringBuilder sb, int[] numbers, int N, int M, int cnt, int start) {
		if(cnt == M) {
			for (int i = 0; i < numbers.length; i++) {
				sb.append(numbers[i] + " ");
			}
			sb.append("\n");
			
			return;
		}
		for (int i = start; i <= N; i++) {
			numbers[cnt] = i;
			displayCombination(sb, numbers, N, M, cnt+1, i+1);
		}
	}
}
