import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] switches = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		int humanNumber = Integer.parseInt(br.readLine());
		for (int i = 0; i < humanNumber; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sex = Integer.parseInt(st.nextToken());
			int switchNumber = Integer.parseInt(st.nextToken());
			controlSwitch(switches, sex, switchNumber);
		}
		
		for (int i = 1; i <= switches.length; i++) {
			System.out.print(switches[i-1] + " ");
			if (i != 0 && i % 20 == 0) {
				System.out.println();
			}
		}
	}
	
	private static void controlSwitch(int[] switches, int sex, int switchNumber) {
		if (sex == 1) {
			controlSwitchMale(switches, switchNumber);
			return;
		}
		if (sex == 2) {
			controlSwitchFemale(switches, switchNumber);
			return;
		}
	}
	
	private static void controlSwitchMale(int[] switches, int switchNumber) {
		for (int i = switchNumber; i <= switches.length; i++) {
			if (i % switchNumber == 0) {
				switches[i - 1] = Math.abs(1 - switches[i - 1]);
			}
		}
	}
	
	private static void controlSwitchFemale(int[] switches, int switchNumber) {
		switchNumber -= 1;
		switches[switchNumber] = Math.abs(1 - switches[switchNumber]);
		for (int i = 1; i < switches.length; i++) {
			int index1 = switchNumber - i;
			int index2 = switchNumber + i;
			if (!(index1 >= 0 && index2 < switches.length 
					&& switches[index1] == switches[index2])) {
				break;
			}
//			if (switches[index1] == switches[index2]) {
				switches[index1] = Math.abs(1 - switches[index1]);
				switches[index2] = Math.abs(1 - switches[index2]);
//			}
		}
	}
	
	
}
