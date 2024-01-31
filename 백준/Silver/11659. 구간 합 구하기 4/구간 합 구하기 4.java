import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] intArray = new int[N+1];
		
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += Integer.parseInt(st.nextToken());
			intArray[i] = sum;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int startIndex = Integer.parseInt(st.nextToken());
			int endIndex = Integer.parseInt(st.nextToken());
			
			sb.append(intArray[endIndex] - intArray[startIndex - 1]);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
//		bw.write(sb.toString());
//		bw.close();
	}
	
}
