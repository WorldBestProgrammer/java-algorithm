import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static class Point implements Comparable {
        int num;
        int height;
        boolean start;
        int end;

        public Point(int num, int height, boolean start) {
            this.num = num;
            this.height = height;
            this.start = start;
        }

        public Point(int num, int height, boolean start, int end) {
            this.num = num;
            this.height = height;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Object o) {
            return this.num - ((Point)o).num;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "num=" + num +
                    ", height=" + height +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] L = new int[N];
        int[] H = new int[N];
        int[] R = new int[N];

        List<Point> buildings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            L[i] = Integer.parseInt(st.nextToken());
            H[i] = Integer.parseInt(st.nextToken());
            R[i] = Integer.parseInt(st.nextToken());

            buildings.add(new Point(L[i], H[i], true, R[i]));
            buildings.add(new Point(R[i], H[i], false));
        }

        Collections.sort(buildings);

        Point firstPoint = buildings.get(0);
        SortedMap<Integer, Integer> result = new TreeMap<>();
        Map<Integer, ArrayList<Integer>> ends = new HashMap<>();
        Point curPoint = new Point(firstPoint.num, firstPoint.height, true, firstPoint.end);
        for (Point building : buildings) {

            if (building.start) {
                if (building.height > curPoint.height) {
                    ArrayList<Integer> integers = ends.getOrDefault(curPoint.end,
                            new ArrayList<>());
                    integers.add(curPoint.height);
                    ends.put(curPoint.end, integers);
                    curPoint = new Point(building.num, building.height, true, building.end);
                } else {
                    ArrayList<Integer> integers = ends.getOrDefault(building.end,
                            new ArrayList<>());
                    integers.add(building.height);
                    ends.put(building.end, integers);
                }
                result.put(building.num, curPoint.height);
            } else {
                if (curPoint.end == building.num) {
                    int tempEnd = -1;
                    int maxHeight = Integer.MIN_VALUE;
                    for (Integer end : ends.keySet()) {
                        if (end == curPoint.end) {
                            ends.remove(end);
                            break;
                        }
                    }
                    for (Integer end : ends.keySet()) {
//                        if (end == curPoint.end) {
//                            ends.remove(end);
//                            continue;
//                        }
                        for (Integer height : ends.get(end)) {
//                            System.out.println("height " + height);
                            if (height >= maxHeight) {
                                maxHeight = height;
                                tempEnd = end;
                            }
                        }
                    }
                    if (maxHeight == Integer.MIN_VALUE) {
                        maxHeight = 0;
                    }
                    curPoint = new Point(0, maxHeight, true, tempEnd);

                } else {
                    ends.remove(building.num);
                }
                result.put(building.num, curPoint.height);
            }
//            System.out.println("point " + building.num + " curPoint " + curPoint);
        }

        int curIndex = -1;
        int curHeight = -1;
        for (Integer i : result.keySet()) {
            if (curHeight != result.get(i)) {
                curIndex = i;
                curHeight = result.get(i);
                sb.append(curIndex).append(" ").append(curHeight).append(" ");
            }
//            System.out.println(i + " " + result.get(i));
        }

        System.out.print(sb.deleteCharAt(sb.length()-1));

    }
}
