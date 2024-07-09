import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int L;
	
	static int result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		L = sc.nextInt();
		
		result = L%5==0? L/5: L/5+1;
		System.out.println(result);
	}
}
