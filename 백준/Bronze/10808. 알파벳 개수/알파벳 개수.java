import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static String S;
	static int[] cnt;
	
	static StringBuilder result = new StringBuilder();
	public static void main(String[] args) {
		test();
	}
	private static void test() {
		testInput();
		testProcess();
		testOutput();
	}
	private static void testInput() {
		S = sc.nextLine();
	}
	private static void testProcess() {
		cnt = new int[26];
		for(int i = 0; i<S.length(); i++) {
			cnt[S.charAt(i)-'a']++;
		}
		for(int i = 0; i<26; i++) {
			result.append(cnt[i]).append(" ");
		}
	}
	private static void testOutput() {
		System.out.println(result);
	}
}
