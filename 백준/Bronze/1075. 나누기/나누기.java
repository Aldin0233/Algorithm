import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static String N;
	static int F;
	
	static String result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() {
		N = sc.nextLine();
		F = sc.nextInt();
	}

	private static void testProcess() {
		N = N.substring(0, N.length()-2);
		for(int i = 0; i<100; i++) {
			String tmp = "";
			if(i<10) {
				tmp += "0";
			}
			tmp += i;
			if(Integer.parseInt(N+tmp)%F==0) {
				result = tmp;
				return;
			}
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
}