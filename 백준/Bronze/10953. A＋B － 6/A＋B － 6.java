import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int T;
	
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		T = Integer.parseInt(sc.nextLine());
		
		for(int i = 0 ; i< T; i++) {
			String str = sc.nextLine();
			
			result.append(str.charAt(0) - '0' + str.charAt(2) - '0').append("\n");
		}
		
		System.out.println(result);
	}
}
