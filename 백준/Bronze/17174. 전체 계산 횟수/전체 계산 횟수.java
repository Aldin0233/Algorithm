import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int N, M;
	
	static int result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
	}

	private static void testInput() {
		N = sc.nextInt(); M = sc.nextInt();
		testProcess();
	}

	private static void testProcess() {
		result += N;
		while(N >= M) {
			N /= M;
			result += N;
		}
		testOutput();
	}

	private static void testOutput() {
		System.out.println(result);
	}
}