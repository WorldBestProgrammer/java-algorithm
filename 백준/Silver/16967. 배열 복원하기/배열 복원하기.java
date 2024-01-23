import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int H = Integer.parseInt(stringTokenizer.nextToken());
        int W = Integer.parseInt(stringTokenizer.nextToken());
        int X = Integer.parseInt(stringTokenizer.nextToken());
        int Y = Integer.parseInt(stringTokenizer.nextToken());

        int[][] B = new int[H+X][W+Y];
        for (int i = 0; i < H + X; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < W + Y; j++) {
                B[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

      
        int[][] A = new int[H][W];
        initRectangle(A, B, H, W, X, Y);
        findOriginalValueFromNotMovingRectangle(A, B, H, W, X, Y);
        findOriginalValueFromMovingRectangle(A, B, H, W, X, Y);
        findOriginalValueFromTwoRectangles(A, B, H, W, X, Y);
        displayOriginalRectangle(A, B, H, W, X, Y);

    }

    private static void initRectangle(int[][] A, int[][] B, int H, int W, int X, int Y) {
      for (int i = 0; i < H; i++) {
        for (int j = 0; j < W; j++) {
          A[i][j] = -1;
        }
      }
    }
  
    private static void findOriginalValueFromNotMovingRectangle(int[][] A, int[][] B, int H, int W, int X, int Y) {
      for (int i = 0; i < X; i++) {
        for (int j = 0; j < W; j++) {
          A[i][j] = B[i][j];
        }
      }

      for (int i = X; i < H; i++) {
        for (int j = 0; j < Y; j++) {
          A[i][j] = B[i][j];
        }
      }
    }

  private static void findOriginalValueFromMovingRectangle(int[][] A, int[][] B, int H, int W, int X, int Y) {
    for (int i = X; i < H; i++) {
      for (int j = W; j < W+Y; j++) {
        A[i-(X)][j-(Y)] = B[i][j];
      }
    }

    for (int i = H; i < H + X; i++) {
      for (int j = Y; j < W; j++) {
        A[i-(X)][j-(Y)] = B[i][j];
      }
    }
  }

  private static void findOriginalValueFromTwoRectangles(int[][] A, int[][] B, int H, int W, int X, int Y) {
    for (int i = X; i < H; i++) {
      for (int j = Y; j < W; j++) {
        if (A[i][j] == -1) {
          A[i][j] = B[i][j] - A[i-X][j-Y];
        } else {
          A[i-X][j-Y] = B[i][j] - A[i][j];
        }

      }
    }
  }

  private static void displayOriginalRectangle(int[][] A, int[][] B, int H, int W, int X, int Y) {
    for (int i =0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        System.out.print(A[i][j] + " ");
      }
      System.out.println();
    }
  }
}