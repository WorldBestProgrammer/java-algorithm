import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.omg.CORBA.INTERNAL;

import jdk.internal.dynalink.beans.StaticClass;
import jdk.nashorn.internal.runtime.FindProperty;
import sun.security.util.Length;

public class Main {
	static int M;
	static List<int[]> numbers;
	static int[] temp;
	static int count;
	static List<int[]> chickens;
	static List<int[]> homes;
	static List<int[]> copyChickens;
	static int minimumDistance = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		chickens = new ArrayList<>();
		homes = new ArrayList<>();		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int point = Integer.parseInt(st.nextToken());
				if (point == 0) {
					continue;
				} else if (point == 1) {
					homes.add(new int[] {i, j});
				} else {
					chickens.add(new int[] {i, j});
				}
			}
		}
		count = 0;
		temp = new int[M];
		numbers = new ArrayList<>();
		comb(0, 0);
		
		for (int[] indexes : numbers) {
			copyChickens = new ArrayList<>();
			for (int index : indexes) {
				copyChickens.add(chickens.get(index));
			}
			findMinimumChickenDistance();
			
		}
		System.out.println(minimumDistance);
	}

	private static void findMinimumChickenDistance() {
		int sum = 0;
		for (int[] home : homes) {
			int chickenDistance = Integer.MAX_VALUE;
			for (int[] copyChicken : copyChickens) {
				int distance = Math.abs(home[0] - copyChicken[0]) + Math.abs(home[1] - copyChicken[1]);
				chickenDistance = Math.min(chickenDistance, distance);
			}
			sum += chickenDistance;
			if (sum > minimumDistance) {
				continue;
			}
		}
		minimumDistance = Math.min(minimumDistance, sum);
	}

	private static void comb(int start, int depth) {
		if (count == M) {
			numbers.add(Arrays.copyOf(temp, temp.length));
			return;
		}
		for (int i = start; i < chickens.size(); i++) {
			temp[count++] = i;
			comb(i+1, depth+1);
			count--;
		}
	}
}
