import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int A, P, C;
	
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
		A = sc.nextInt(); P = sc.nextInt(); C = sc.nextInt();
	}

	private static void testProcess() {
		result = A+C>P? A+C:P;
	}

	private static void testOutput() {
		System.out.println(result);
	}
}
