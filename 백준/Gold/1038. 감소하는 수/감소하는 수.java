import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int N;
	static List<Long> arr = new ArrayList<>();
	
	static long result;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() {
		N = sc.nextInt();
	}

	private static void testProcess() {
		runComb(new StringBuilder(), 9);
		if(N>= arr.size()) {
			result = -1;
			return;
		}
		Collections.sort(arr);
		result = arr.get(N);
	}

	private static void runComb(StringBuilder sb, int now) {
		if(sb.length()>0) {
			arr.add(Long.parseLong(sb.toString()));
		}
		for(int i = now; i>=0; i--) {
			sb.append(i);
			runComb(sb, i-1);
			sb.deleteCharAt(sb.length()-1);
		}
	}

	private static void testOutput() {
		System.out.println(result);
	}
}