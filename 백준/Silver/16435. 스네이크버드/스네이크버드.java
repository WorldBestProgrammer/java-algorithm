import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		List<Integer> apple = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			apple.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(apple);
		
		for (int i = 0; i < N; i++) {
			if (L >= apple.get(i)) {
				L++;
			} else {
				break;
			}
		}
		
		System.out.println(L);
	}
	}
