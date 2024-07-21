import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int idx, sum; 
	
	static ScoreResult result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		process: {
			for(int i = 0; i< 9; i++) {
			int tmp = sc.nextInt();
			if(((i/2) + 1) * 100 < tmp) {
				result = ScoreResult.hacker;
				break process;
			}
			sum += tmp;
			}
			if(sum >= 100) {
				result = ScoreResult.draw;
			} else {
				result = ScoreResult.none;
			}
		}
			
		testOutput();
	}

	private static void testOutput() {
		System.out.println(result);
	}
}

enum ScoreResult {
	draw, none, hacker;
}
