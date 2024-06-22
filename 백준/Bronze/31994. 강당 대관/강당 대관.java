import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int max;
	
	static String result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		for(int i = 0 ; i < 7; i++) {
			String subject = sc.next();
			int people = sc.nextInt();
			if(people>max) {
				max = people;
				result = subject;
			}
		}
		System.out.println(result);
	}
}