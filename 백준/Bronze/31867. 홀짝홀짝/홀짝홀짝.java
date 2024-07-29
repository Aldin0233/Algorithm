import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int N;
	static String K;
	static int A, B;
	
	static int result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
	}

	private static void testInput() {
		N = Integer.parseInt(sc.nextLine());
		K = sc.nextLine();
		testProcess();
	}

	private static void testProcess() {
		for(int i = 0 ; i < N; i++) {
			if((K.charAt(i) - '0')%2 == 0) {
				A++;
			} else {
				B++;
			}
		}
		if(A > B) result = 0;
		else if(A < B) result = 1;
		else result = -1;
		testOutput();
	}

	private static void testOutput() {
		System.out.println(result);
	}
}