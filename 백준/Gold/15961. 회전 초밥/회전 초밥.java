import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int maxKind;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] sushi = new int[N];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		maxKind = Integer.MIN_VALUE;
		int[] bucket = new int[d+1];
		int count = 0;
		for (int i = 0; i < k; i++) {
			if (bucket[sushi[i]] == 0) {
				count++;
			}
			bucket[sushi[i]]++;
			
		}
		maxKind = Math.max(maxKind, count);
		for (int i = 0; i < N; i++) {
			if (bucket[sushi[i]] == 1) {
				count--;
			}
			bucket[sushi[i]]--;
			
			int inIndex = (i + k) % N;
			if (bucket[sushi[inIndex]] == 0) {
				count++;
			}
			bucket[sushi[inIndex]]++;
			
			if (bucket[c] == 0) {
				count++;
				maxKind = Math.max(maxKind, count);
				count--;
			} else {
				maxKind = Math.max(maxKind, count);
			}
		}
		System.out.println(maxKind);
		
	}
}
