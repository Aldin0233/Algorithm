import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static String[] info = {"black", "brown", "red", "orange", 
			"yellow", "green", "blue", "violet", "grey", "white"};
	static int A, B, C;
	
	static StringBuilder result= new StringBuilder();
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		A = findInfo(sc.nextLine());
		if(A != 0) {			
			result.append(A);		
		}
		B = findInfo(sc.nextLine());
		if(!(result.length()==0 && B == 0)) {			
			result.append(B);
		}
		if(result.length()!=0) {
			C = findInfo(sc.nextLine());
			result.append(makeOm(C));
		} else {
			sc.nextLine();
			result.append("0");
		}
		
		System.out.println(result);
	}

	private static Object makeOm(int info2) {
		String str = "";
		for(int i = 0 ; i<info2; i++) {
			str += "0";
		}
		return str;
	}

	private static int findInfo(String nextLine) {
		for(int i = 0 ; i<10; i++) {
			if(info[i].equals(nextLine)) 				
				return i;
		}
		return -1;
	}
}