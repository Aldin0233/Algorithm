import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int T;
	static String testCase;
	static int repeatO, caseResult;
	
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		T = Integer.parseInt(sc.nextLine());
		for(int tc = 1; tc<= T; tc++) {			
			testInput();
			testProcess();
		}
		testOutput();
	}

	private static void testInput() {
		testCase = sc.nextLine();
	}

	private static void testProcess() {
		caseResult = 0; repeatO = 0;
		for(int i = 0 ; i < testCase.length(); i++) {
			if(testCase.charAt(i) == 'O') {
				caseResult += ++repeatO;
			} else {
				repeatO = 0;
			}
		}
		result.append(caseResult).append("\n");
	}

	private static void testOutput() {
		System.out.println(result);
	}
}
