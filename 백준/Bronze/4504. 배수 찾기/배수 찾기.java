import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int N;
	
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		N = sc.nextInt();
		int input = sc.nextInt();
		while(input != 0) {
			result.append(input).append(" is ");
			if(input%N!=0) {
				result.append("NOT ");
			}
			result.append("a multiple of ").append(N).append(".\n");
			input = sc.nextInt();
		}
		System.out.println(result);
	}
}
