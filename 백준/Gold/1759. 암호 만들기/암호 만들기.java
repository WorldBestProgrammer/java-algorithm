import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L;
	static int C;
	static char[] chars;
	static char[] alphabet;
	static String vowels = "aeiou";
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		alphabet = new char[C];
		for (int i = 0; i < C; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}
		
		chars = new char[L];
		Arrays.sort(alphabet);
		comb(0, 0);
		System.out.println(sb);
		
	}

	private static void comb(int start, int depth) {
		if (depth == L) {
			int vowel = 0;
			int consonant = 0;
			for (int i = 0; i < L; i++) {
				boolean flag = true;
				for (char c : vowels.toCharArray()) {
					if (c == chars[i]) {
						vowel++;
						flag = false;
						break;
					}
				}
				if (flag) {
					consonant++;
				}
			}
			if (vowel >= 1 && consonant >= 2) {
				sb.append(chars).append("\n");				
			}
			
			return;
		}
		for (int i = start; i < C; i++) {
			chars[depth++] = alphabet[i];
			comb(i+1, depth);
			depth--;
		}
	}
}
