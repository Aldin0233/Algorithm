import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int A, B;
	
	static int result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() {
		A = sc.nextInt(); B = sc.nextInt();
	}

	private static void testProcess() {
		result = A + B;
	}

	private static void testOutput() {
		System.out.println(result);
	}
}