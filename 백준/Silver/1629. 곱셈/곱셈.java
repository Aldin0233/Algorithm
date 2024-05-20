import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static long A, B, C;
	
	static int result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
		result = (int) testProcess(B);
		testOutput();
	}

	private static void testInput() {
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
	}

	private static long testProcess(long remain) {
		if(remain == 0) {
			return 1;
		} 
		long halfRemain = testProcess(remain/2);
		long tmpResult = (halfRemain* halfRemain)%C;
		if(remain%2==1) {
			tmpResult = (tmpResult*A)%C;
		}
		return tmpResult;
	}

	private static void testOutput() {
		System.out.println(result);
	}
}
