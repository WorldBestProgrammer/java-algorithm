import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] cards = new int[N];
		for (int i = 0; i < N; i++) {
			cards[i] = sc.nextInt();
		}
		
		int maxSum = findMaxSum(cards, M);
		System.out.println(maxSum);
	}

	public static int findMaxSum(int[] cards, int M){
		int maxSum = 0;
		//sorting
		for (int i = 0; i < cards.length; i++) {
			int sum = 0;
			sum += cards[i];
			for (int j = i+1; j < cards.length; j++) {
				sum += cards[j];
				for (int k = j+1; k < cards.length; k++) {
					sum += cards[k];
					if (sum <= M) {
					maxSum = Math.max(maxSum, sum); 
					}
					sum -= cards[k];
				}
				sum -= cards[j];
			}
			sum -= cards[i];
		}
		return maxSum;
	}
}
