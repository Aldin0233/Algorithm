import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int N;
	
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		N = Integer.parseInt(sc.nextLine());
		for(int i = 0 ; i<N ; i++) {
			String password = sc.nextLine();
			if(password.length() < 6 || password.length() > 9) {
				result.append("no");
			} else {
				result.append("yes");
			}
			result.append("\n");
		}
		System.out.println(result);
	}
}