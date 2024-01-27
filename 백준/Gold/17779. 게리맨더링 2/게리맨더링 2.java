import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Node {
        int population;
        int areaNumber;

        public Node(int population) {
            this(population, 0);
        }

        public Node(int population, int areaNumber) {
            this.population = population;
            this.areaNumber = areaNumber;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stringTokenizer;
        Node[][] A = new Node[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                A[i][j] = new Node(Integer.parseInt(stringTokenizer.nextToken()));
            }
        }

        int minGap = Integer.MAX_VALUE;
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {
                        if (x + d1 <= N && y - d1 >= 1 && x + d2 <= N && y + d2 <= N
                        && x + d1 + d2 <= N && y - d1 + d2 >=1 && y - d1 + d2 <= N) {
                            initializeAreaNumber(A, N);
                            divideArea(A, N, x, y, d1, d2);
                            int gap = findGap(A, N);
//                            System.out.println(gap);
                            minGap = Math.min(minGap, gap);
                        }
                    }
                }
            }
        }
        System.out.println(minGap);


    }

    private static void initializeAreaNumber(Node[][] A, int size) {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                A[i][j].areaNumber = 0;
            }
        }
    }
    private static int findGap(Node[][] A, int size) {
        HashMap<Integer, Integer> populationPerArea = new HashMap<>();
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                populationPerArea.put(A[i][j].areaNumber,
                        populationPerArea.getOrDefault(A[i][j].areaNumber, 0) + A[i][j].population);
            }
        }

        Collection<Integer> values = populationPerArea.values();
//        System.out.println("values = " + values);
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (Integer population : values) {
            max = Math.max(max, population);
            min = Math.min(min, population);
        }
//        display(A);
        return max - min;

    }
    private static void display(Node[][] graph) {
        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph[0].length; j++) {
                System.out.print(graph[i][j].areaNumber + " ");
            }
            System.out.println();
        }
    }
    // 어떻게 풀어야 할까?
    // 변하지 않는 것은 무엇일까? => x, y, d1, d2가 주어졌을때 선거구를 구하는 방법
    // 변하는 것은 무엇일까? x, y, d1, d2 => 이 조건을 변경해보자
    private static void divideArea(Node[][] area, int size, int x, int y, int d1, int d2) {
        for (int i = 0; i <= d1; i++) {
            area[x+i][y-i].areaNumber = 5;
        }
        for (int i = 0; i <= d2; i++) {
            area[x+i][y+i].areaNumber = 5;
        }
        for (int i = 0; i <= d2; i++) {
            area[x+d1+i][y-d1+i].areaNumber = 5;
        }
        for (int i = 0; i <= d1; i++) {
            area[x+d2+i][y+d2-i].areaNumber = 5;
        }

        for (int r = 1; r < x + d1; r++) {
            for (int c = 1; c <= y; c++) {
                if (area[r][c].areaNumber == 5) {
                    break;
                }
                area[r][c].areaNumber = 1;
            }
        }

//        for (int r = 1; r <= x + d2; r++) {
//            for (int c = y + 1; c <= size; c++) {
//                if (area[r][c].areaNumber == 5) {
//                    continue;
//                }
//                area[r][c].areaNumber = 2;
//            }
//        }

        for (int r = 1; r <= x + d2; r++) {
            for (int c = size; c >= y + 1; c--) {
                if (area[r][c].areaNumber == 5) {
                    break;
                }
                area[r][c].areaNumber = 2;
            }
        }

        for (int r = x + d1; r <= size; r++) {
            for (int c = 1; c < y - d1 + d2; c++) {
                if (area[r][c].areaNumber == 5) {
                    break;
                }
                area[r][c].areaNumber = 3;
            }
        }

//        for (int r = x + d2 + 1; r <= size; r++) {
//            for (int c = y - d1 + d2; c <= size; c++) {
//                if (area[r][c].areaNumber == 5) {
//                    continue;
//                }
//                area[r][c].areaNumber = 4;
//            }
//        }

        for (int r = x + d2 + 1; r <= size; r++) {
            for (int c = size; c >= y - d1 + d2; c--) {
                if (area[r][c].areaNumber == 5) {
                    break;
                }
                area[r][c].areaNumber = 4;
            }
        }

        for (int r = 1; r <= size; r++) {
            for (int c = 1; c <= size; c++) {
                if (area[r][c].areaNumber == 0) {
                    area[r][c].areaNumber = 5;
                }
            }
        }
    }

    private static int[][] makeDividedArea(int size, int x, int y, int d1, int d2) {
        int[][] dividedArea = new int[size + 1][size + 1];
        for (int i = 0; i <= d1; i++) {
            dividedArea[x+i][y-i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            dividedArea[x+i][y+i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            dividedArea[x+d1+i][y-d1+i] = 5;
        }
        for (int i = 0; i <= d1; i++) {
            dividedArea[x+d2+i][y+d2-i] = 5;
        }

        for (int r = 1; r < x + d1; r++) {
            for (int c = 1; c <= y; c++) {
                if (dividedArea[r][c] == 5) {
                    break;
                }
                dividedArea[r][c] = 1;
            }
        }

        for (int r = 1; r <= x + d2; r++) {
            for (int c = y + 1; c <= size; c++) {
                if (dividedArea[r][c] == 5) {
                    continue;
                }
                dividedArea[r][c] = 2;
            }
        }

        for (int r = x + d1; r <= size; r++) {
            for (int c = 1; c < y - d1 + d2; c++) {
                if (dividedArea[r][c] == 5) {
                    break;
                }
                dividedArea[r][c] = 3;
            }
        }

        for (int r = x + d2 + 1; r <= size; r++) {
            for (int c = y - d1 + d2; c <= size; c++) {
                if (dividedArea[r][c] == 5) {
                    continue;
                }
                dividedArea[r][c] = 4;
            }
        }

        for (int r = 1; r <= size; r++) {
            for (int c = 1; c <= size; c++) {
                if (dividedArea[r][c] == 0) {
                    dividedArea[r][c] = 5;
                }
            }
        }

        return dividedArea;
    }
}
