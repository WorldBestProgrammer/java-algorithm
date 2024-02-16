import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static int[][] copyMap;
	static List<Point> enemies;
	static int[] positions;
	static List<int[]> positionsCombination = new ArrayList<>();
	static int time;
	static int count;
	static int N;
	static int M;
	static int D;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		enemies = new ArrayList<>();
//		map = new int[N+1][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
//				if (i == N) {
//					map[i][j] = 100;
//				}
//				map[i][j] = Integer.parseInt(st.nextToken());
				if (Integer.parseInt(st.nextToken()) == 1) {
					enemies.add(new Point(i, j));
				}
			}
		}
		
//		for (Point point : enemies) {
//			System.out.println(point);
//		}
		
//		copyMap = new int[N+1][M];
//		for (int i = 0; i <= N; i++) {
//			for (int j = 0; j < M; j++) {
//				copyMap[i][j] = map[i][j];
//			}
//		}
		//  조합 MC3;
		
//		List<Point> copyEnemies = new ArrayList<>();
//		List<Point> copyEnemies = Collections.unmodifiableList(enemies);
		List<Point> copyEnemies = new ArrayList<>();
		
		for (int i = 0; i < enemies.size(); i++) {
			copyEnemies.add(new Point(enemies.get(i).x, enemies.get(i).y));
		}
		positions = new int[3];
		Combination(0, 0);
		int maxCount = Integer.MIN_VALUE;
		for (int[] points : positionsCombination) {
			enemies = new ArrayList<>();
			for (int i = 0; i < copyEnemies.size(); i++) {
				enemies.add(new Point(copyEnemies.get(i).x, copyEnemies.get(i).y));
			}
//			System.out.println(Arrays.toString(points));
//			System.out.println(Arrays.toString(points));
			count = 0;
			time = 0;
			while(turn(points)) {
				time++;
			}
			maxCount = Math.max(maxCount, count);	
		}
		
		System.out.println(maxCount);
	}
	private static void Combination(int depth, int start) {
		if (depth == 3) {
			int[] temp = new int[3];
			for (int i = 0; i < 3; i++) {
				temp[i] = positions[i];
			}
			positionsCombination.add(temp);
			return;
		}
		for (int i = start; i < M; i++) {
			positions[depth++] = i;
			Combination(depth, i+1);
			depth--;
		}
	}
	private static boolean turn(int[] positions) {
		Set<Integer> set = new HashSet<>();
		for (int position : positions) {
			int x = N;
			int y = position;
			int minDistance = Integer.MAX_VALUE;
			int attackIndex = -1;
			for (int i = 0; i < enemies.size(); i++) {
				Point enemyPoint = enemies.get(i);
				int distance = Math.abs(x - enemyPoint.x) + Math.abs(y - enemyPoint.y);
				if (distance <= D) {
					if (distance < minDistance) {
						minDistance = distance;
						attackIndex = i;
					} else if (distance == minDistance) {
						if (enemies.get(attackIndex).y - enemyPoint.y > 0) {
							attackIndex = i;
						}
					}
				}
			}
			if (minDistance != Integer.MAX_VALUE) {
				set.add(attackIndex);
			}	
		}
		
		List<Point> deleteList = new ArrayList<>();
		for (int index : set) {
			count++;
			deleteList.add(enemies.get(index));
		}
//		System.out.println("제거될 대상들" + time);
		for (Point dPoint : deleteList) {
			
//			System.out.println(dPoint);
			enemies.remove(dPoint);
		}
//		System.out.println();
		
		List<Point> newList = new ArrayList<>();
//		System.out.println("남은 적들" + time);
		for (int i = 0; i < enemies.size(); i++) {
			Point point = enemies.get(i);
//			System.out.println(point);
			if (point.x + 1 == N) {
				continue;
			}
			point.x += 1;
			newList.add(point);
			
		}
		
		enemies = newList;
		if (enemies.size() > 0) {
			return true;
		}
		return false;
		
	}
}
