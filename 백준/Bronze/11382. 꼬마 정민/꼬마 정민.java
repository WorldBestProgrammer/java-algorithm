import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long sum = 0;
        for (int i = 0; i < 3; i++) {
            long number = scanner.nextLong();
            sum += number;
        }
        System.out.println(sum);
    }
    }
