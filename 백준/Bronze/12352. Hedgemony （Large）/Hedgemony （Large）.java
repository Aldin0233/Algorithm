import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int T;
	static int N;
	static double[] bushs;
	
	static double result;
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc<=T; tc++) {			
			testInput();
			testProcess();
			testOutput(tc);
		}
	}

	private static void testInput() throws IOException {
		N = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		bushs = new double[N];
		for(int i = 0 ; i<N; i++) {
			bushs[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void testProcess() {
		for(int i = 1 ; i<N-1; i++) {
			bushs[i] = Math.min(bushs[i], (bushs[i - 1] + bushs[i + 1])/2);
		}
		result = bushs[N-2];
	}

	private static void testOutput(int tc) {
		System.out.printf("Case #%d: %.6f\n", tc, result);
	}
}