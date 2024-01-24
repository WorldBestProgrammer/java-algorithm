import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int N = Integer.parseInt(br.readLine());
      int F = Integer.parseInt(br.readLine());

      int behindTwoDigitAllZeroNumber = (N / 100) * 100;
      for (int i = 0; i < 100; i++) {
        int behindTwoDigitChangedNumber = behindTwoDigitAllZeroNumber + i;
        if (behindTwoDigitChangedNumber % F == 0) {
          BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
          for (int j = 0; j < (2 - String.valueOf(i).length()); j++){
          bw.write("0");
          }
          bw.write(String.valueOf(i));
          // bw.write(i);
          bw.flush();
          return ;
        }
      }
      
  }
}