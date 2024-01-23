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
        for (int i = 0; i < H; i++) {
        	for (int j = 0; j < W; j++) {
        		A[i][j] = -1;
        	}
        }
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
        
        for (int i = X; i < H; i++) {
        	for (int j = Y; j < W; j++) {
        		if (A[i][j] == -1) {
        			A[i][j] = B[i][j] - A[i-X][j-Y];
        		} else {
        			A[i-X][j-Y] = B[i][j] - A[i][j];
        		}
        		
        	}
        }
        
        for (int i =0; i < H; i++) {
        	for (int j = 0; j < W; j++) {
        		System.out.print(A[i][j] + " ");
        	}
        	System.out.println();
        }
    }
}