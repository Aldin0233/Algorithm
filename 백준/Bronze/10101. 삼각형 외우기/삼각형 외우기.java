import java.util.Scanner;

public class Main {
	static Scanner sc =new Scanner(System.in);
	static int A, B, C, result;
	public static void main(String[] args) {
		test();
	}
	private static void test() {
		testInput();
		testProcess();
		testOutput();
	}
	private static void testInput() {
		A= sc.nextInt();
		B= sc.nextInt();
		C= sc.nextInt();
		
	}
	private static void testProcess() {
		if(A+B+C==180) {
			if(A==B && B==C) {
				result=1;
			} else if(A==B || B==C || A==C) {
				result=2;
			} else {
				result=3;
			}
		} 
	}
	private static void testOutput() {
		if(result==1) {
			System.out.println("Equilateral");
		} else if(result==2) {
			System.out.println("Isosceles");
		} else if(result==3) {
			System.out.println("Scalene");
		} else {
			System.out.println("Error");
		}
	}
}