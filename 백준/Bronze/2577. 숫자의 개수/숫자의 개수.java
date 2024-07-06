import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static long ABC;
	static int[] arr = new int[10];

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
		ABC = sc.nextLong() * sc.nextInt() * sc.nextInt();
	}

	private static void testProcess() {
		while(ABC > 0) {
			arr[(int)(ABC%10)]++;
			ABC/=10;
		}
		for(int i = 0 ; i<10; i++) {
			result.append(arr[i]).append("\n");
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
}
