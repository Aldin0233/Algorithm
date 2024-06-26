import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int N;
	static boolean[] seat;
	
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
		N = sc.nextInt(); sc.nextLine();
		String nextLine = sc.nextLine();
		sc = new Scanner(nextLine);
	}

	private static void testProcess() {
		seat = new boolean[101];
		for(int i = 0 ; i< N; i++) {
			int wantToSeat = sc.nextInt();
			if(seat[wantToSeat]) {
				result++;
			} else {
				seat[wantToSeat] = true;
			}
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
}
