
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int N;
	
	static StringBuilder A = new StringBuilder(), B = new StringBuilder();
	
	static String result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
	}

	private static void testInput() {
		N = sc.nextInt();
		for(int i = 0; i < N; i++) {
			A.append(sc.next());
		}
		for(int i = 0 ; i< N; i++) {
			B.append(sc.next());
		}
		testProcess();
	}

	private static void testProcess() {
		result = compare(A.toString(), B.toString()) <= 0 ? A.toString() : B.toString();
		testOutput();
	}

	private static int compare(String X, String Y) {
		if(X.length() == Y.length()) {
			return X.compareTo(Y);
		}
		return X.length()-Y.length();
	}

	private static void testOutput() {
		System.out.println(result);
	}
}