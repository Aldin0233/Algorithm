import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int H, I, A, R, C;
	
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
		H = sc.nextInt(); I = sc.nextInt();
		A = sc.nextInt(); R = sc.nextInt(); C = sc.nextInt();
	}

	private static void testProcess() {
		result = (H * I) - (A * R * C);
	}

	private static void testOutput() {
		System.out.println(result);
	}
}