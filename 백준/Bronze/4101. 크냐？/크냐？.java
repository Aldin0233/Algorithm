import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int A, B;
	
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		testInput();
		testOutput();
	}

	private static void testInput() throws IOException {
		while(true) {
			st = new StringTokenizer(br.readLine().trim());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			if(A == 0 && B == 0) return;
			testProcess();
		}
	}

	private static void testProcess() {
		if(A > B) {
			result.append("Yes\n");
		} else {
			result.append("No\n");
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
}
