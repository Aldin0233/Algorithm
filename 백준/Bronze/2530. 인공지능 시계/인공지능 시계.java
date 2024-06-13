import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int A, B, C, D;
	
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
		A = sc.nextInt(); B = sc.nextInt(); C = sc.nextInt(); D = sc.nextInt();
	}

	private static void testProcess() {
		C += D; B += C / 60; A += B / 60;
		C = C % 60; B = B % 60; A = A % 24;
		result.append(A).append(" ").append(B).append(" ").append(C);
	}

	private static void testOutput() {
		System.out.println(result);
	}
}