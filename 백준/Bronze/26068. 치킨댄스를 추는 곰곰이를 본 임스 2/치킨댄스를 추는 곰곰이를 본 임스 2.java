import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int  N;
	
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		test();
	}

	private static void test() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine().trim());
		
		for(int i = 0 ; i<N; i++) {
			int gift = Integer.parseInt(br.readLine().trim().substring(2));
			
			if(gift<=90) {
				result++;
			}
		}
		System.out.println(result);
	}
}
