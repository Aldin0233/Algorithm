import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static long AB, CD;
	
	static long result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		AB = Long.parseLong(sc.next() + sc.next());
		CD = Long.parseLong(sc.next() + sc.next());
		result = AB + CD;
		System.out.println(result);
	}
}