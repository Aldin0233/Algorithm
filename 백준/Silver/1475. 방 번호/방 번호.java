import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static String N;
	static int[] arr = new int[10];
	
	static int result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		N = sc.nextLine();
		for(int i = 0; i< N.length(); i++) {
			arr[N.charAt(i) - '0']++;
		}
		for(int i = 0; i< 10; i++) {
			if(i!=6 && i!=9) {
				result = Math.max(arr[i], result);
			}
		}
		int tmp = arr[6]+arr[9];
		result = Math.max(tmp%2==1? tmp/2 + 1: tmp/2, result);
		System.out.println(result);
	}
	
}