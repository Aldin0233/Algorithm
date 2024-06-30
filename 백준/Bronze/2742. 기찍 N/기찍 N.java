import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int N;
	
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() {
		N = sc.nextInt();
	}

	private static void testProcess() {
		for(int i = N ; i > 0; i--) {
			result.append(i).append("\n");
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
}