import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	static int[] A, B;
	
	static int result;
	
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
		A = new int[N]; B = new int[N];
		StringTokenizer st1 = new StringTokenizer(br.readLine().trim());
		StringTokenizer st2 = new StringTokenizer(br.readLine().trim());
		for(int i = 0 ; i< N; i++) {
			A[i] = Integer.parseInt(st1.nextToken());
			B[i] = Integer.parseInt(st2.nextToken());
		}
	}

	private static void testProcess() {
		Arrays.sort(A); Arrays.sort(B);
		for(int i = 0 ; i<N; i++) {
			result += (A[i] * B[N-1-i]);
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
	
}