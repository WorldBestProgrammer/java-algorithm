import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // key: 높이, value: 위치
        TreeMap<Integer, List<Integer>> heights = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            if (!heights.containsKey(height)) {
                heights.put(height, new ArrayList<>());
            }
            heights.get(height).add(i);
        }

        int maxArea = Integer.MIN_VALUE;
        TreeSet<Integer> used = new TreeSet<>();
        for (Integer height : heights.keySet()) {
            for (int loc : heights.get(height)) {
                // 왼쪽 끝 찾기
                int left = 0;
                Integer lower = used.lower(loc);
                if (lower != null) {
                    left = lower + 1;
                }
                // 오른쪽 끝 찾기
                int right = N - 1;
                Integer higher = used.higher(loc);
                if (higher != null) {
                    right = higher - 1;
                }
                // 넓이 구해주기
                int area = (right - left + 1) * height;
                // 최대 넓이 갱신
                maxArea = Math.max(maxArea, area);
                // 사용한 것 넣기
                used.add(loc);
            }
            // 사용한 것 넣기
//            used.addAll(heights.get(height));
        }
        System.out.println(maxArea);
    }
}
