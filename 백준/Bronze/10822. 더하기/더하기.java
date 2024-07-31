import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static String line;
	
	static long result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
	}

	private static void testInput() {
		line = sc.nextLine();
		testProcess();
	}

	private static void testProcess() {
		StringTokenizer st = new StringTokenizer(line, ",");
		while(st.hasMoreTokens()) {
			result += Integer.parseInt(st.nextToken());
		}
		testOutput();
	}

	private static void testOutput() {
		System.out.println(result);
	}
}
