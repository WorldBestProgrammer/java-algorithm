import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static boolean[] comb;
	static List<Integer>[] graph;
	static int[] people;
	static int N;
	static int minPopulation;
	static boolean[] visited;
	static int truePopulation;
	static int falsePopulation;
	static int count = 0;
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		st = new StringTokenizer(br.readLine());
		graph = new List[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int iter = Integer.parseInt(st.nextToken());
			for (int j = 0; j < iter; j++) {
				int a = Integer.parseInt(st.nextToken());
				graph[i].add(a);
			}
		}

		minPopulation = Integer.MAX_VALUE;
		comb = new boolean[N+1];
		dfs(2);
		if (minPopulation == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minPopulation);
		}
	}

	private static void dfs(int depth) {
		if (depth == N+1) {
			int trueTarget = 0;
			int falseTarget = 0;
			int trueStart = -1;
			int falseStart = -1;
			for (int i = 1; i <= N; i++) {
				if (comb[i] == true) {
					trueTarget++;
					trueStart = i;
				} else {
					falseTarget++;
					falseStart = i;
				}
			}
			if (trueStart == -1 || falseStart == -1) {
				return;
			}
			visited = new boolean[N+1];
			visited[trueStart] = true;
			truePopulation = 0;
			count = 0;
			flag = false;
			isValid(trueStart, true, 1, trueTarget);
			boolean flag1 = flag;
			visited = new boolean[N+1];
			visited[falseStart] = true;
			falsePopulation = 0;
			count = 0;
			flag = false;
			isValid(falseStart, false, 1, falseTarget);
			boolean flag2 = flag;
			if (flag1 && flag2) {
				minPopulation = Math.min(minPopulation, Math.abs(truePopulation - falsePopulation));
			}
			return;
		}
		comb[depth] = false;
		dfs(depth+1);
		comb[depth] = true;
		dfs(depth+1);
	}

	private static void isValid(int start, boolean std, int depth, int target) {
		count++;
		if (std == true) {
			truePopulation += people[start];
		} else {
			falsePopulation += people[start];
		}
		
		if (count == target) {
			flag = true;
			return;
		}
		
		for (int next : graph[start]) {
			if (comb[next] == std && !visited[next]) {
				
				visited[next] = true;
				isValid(next, std, depth + 1, target);
				if (flag) {
					return;
				}
			}
		}
	}
}
