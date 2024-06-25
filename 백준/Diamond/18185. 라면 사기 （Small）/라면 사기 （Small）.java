import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static int[] factoryOrder;
	
	static long result;
	
	public static void main(String[] args) throws IOException {
		test();
	}

	private static void test() throws IOException {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() throws IOException {
		N = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		factoryOrder = new int[N];
		for(int i = 0 ; i<N; i++) {
			factoryOrder[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void testProcess() {
		for(int i = N-1; i > 1; i--) {
			int tmp, order;
			if(factoryOrder[i-1] > factoryOrder[i-2]) {
				tmp = factoryOrder[i-1] - factoryOrder[i-2];
				order = Math.min(factoryOrder[i], tmp);
				result += order * 5;
				factoryOrder[i] -= order;
				factoryOrder[i-1] -= order;
			}
			order = Math.min(factoryOrder[i], Math.min(factoryOrder[i-1], factoryOrder[i-2]));
			result += order * 7;
			factoryOrder[i] -= order;
			factoryOrder[i-1] -= order;
			factoryOrder[i-2] -= order;
			order = Math.min(factoryOrder[i], factoryOrder[i-1]);
			result += order * 5;
			factoryOrder[i] -= order;
			factoryOrder[i-1] -= order;
			result += factoryOrder[i] * 3;
		}
		int order = Math.min(factoryOrder[1], factoryOrder[0]);
		result += order * 5;
		factoryOrder[1] -= order;
		factoryOrder[0] -= order;
		result += factoryOrder[1] * 3;
		result += factoryOrder[0] * 3;
	}

	private static void testOutput() {
		System.out.println(result);
	}
	
}