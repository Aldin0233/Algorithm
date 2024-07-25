import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int A, B, C;
	
	static int result = 1;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() {
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
	}

	private static void testProcess() {
		if(A%2 ==0 && B%2 == 0 && C%2 == 0) {
			result = A * B * C;
		} else {
			if(A %2 == 1) {
				result *= A;
			}
			if(B %2 == 1) {
				result *= B;
			}
			if(C %2 == 1) {
				result *= C;
			}
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
}