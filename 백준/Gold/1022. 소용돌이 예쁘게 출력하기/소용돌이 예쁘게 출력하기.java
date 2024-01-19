import java.awt.font.GraphicAttribute;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int r1 = sc.nextInt();
    int c1 = sc.nextInt();
    int r2 = sc.nextInt();
    int c2 = sc.nextInt();

    int maxR = Math.max(r1, r2) + 5000;
    int minR = Math.min(r1, r2) + 5000;
    int maxC = Math.max(c1, c2) + 5000;
    int minC = Math.min(c1, c2) + 5000;

    // int[][] graph = new int[maxAll * 2 + 1][maxAll * 2 + 1]; 

    int[][] graph = new int[Math.abs(r2-r1)+1][Math.abs(c2-c1)+1];
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, -1, 0, 1};

    int count = 1;
    int goLength = 0;
    int curX = 5000;
    int curY = 5000;

    if (curX >= minC && curX <= maxC && curY >= minR && curY <= maxR) {
      graph[curY-minR][curX-minC] = 1;
    }
    for(int i = 0; i < 5000 ; i++) {
      for(int j = 0; j < 4; j++) {
        if (j == 0 || j == 2) {
          goLength += 1;
        }
        for (int k = 0; k < goLength; k++) {
          int nx = curX + dx[j];
          int ny = curY + dy[j];
          count++;
          if (nx >= minC && nx <= maxC && ny >= minR && ny <= maxR) {
            graph[ny-minR][nx-minC] = count;
          }
          curX = nx;
          curY = ny;
        }
      }
    }
    // goLength++;
    for (int k = 0; k < goLength; k++) {
      int nx = curX + dx[0];
      int ny = curY + dy[0];
      count++;
      if (nx >= minC && nx <= maxC && ny >= minR && ny <= maxR) {
        graph[ny-minR][nx-minC] = count;
      }
      curX = nx;
      curY = ny;
    }

    int maxLen = 0;
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[0].length; j++) {
        maxLen = Math.max(maxLen, Integer.toString(graph[i][j]).length());
      }
    }
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[0].length; j++) {
        for (int k = 0; k < maxLen - Integer.toString(graph[i][j]).length(); k++){
          System.out.print(" ");
          }
        System.out.print(graph[i][j] + " ");
        
      }
      System.out.println();
    }
  }
}