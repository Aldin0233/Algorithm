import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		N = Integer.parseInt(br.readLine().trim());
		
		for(int i = 0; i< N; i++) {
			String s = br.readLine().trim();
			int G = 0; int B = 0;
			for(int j = 0 ; j < s.length(); j++) {
				char c = s.charAt(j);
				if(c == 'G' || c == 'g') {
					G++;
				} else if(c == 'B' || c == 'b') {
					B++;
				}
			}
			result.append(s).append(" is ");
			if(G > B) {
				result.append("GOOD");
			} else if(G < B) {
				result.append("A BADDY");
			} else {
				result.append("NEUTRAL");
			}
			result.append("\n");
		}
		System.out.println(result);
	}
}
