import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static BigInteger N, M;
	
	static BigInteger resultMoney, resultMod;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
		testProcess();
		testOutput();
	}

	private static void testInput() {
		N = sc.nextBigInteger(); M = sc.nextBigInteger();
	}

	private static void testProcess() {
		resultMoney = N.divide(M); resultMod = N.remainder(M); 
	}

	private static void testOutput() {
		System.out.printf("%s\n%s", resultMoney, resultMod);
	}
}
