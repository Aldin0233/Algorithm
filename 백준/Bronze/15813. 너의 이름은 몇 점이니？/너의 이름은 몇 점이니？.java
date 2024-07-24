import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int length;
	static String name;
	
	static int scoreResult;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
	}

	private static void testInput() {
		if(!sc.hasNextLine()) throw new InputException("no length");
		length = Integer.parseInt(sc.nextLine());
		if(!sc.hasNextLine()) throw new InputException("no name");
		name = sc.nextLine();
		testProcess();
	}

	private static void testProcess() {
		if(length <= 0) { testOutput(); return; }
		else if(length > 100) throw new InputException("wrong problem");
		for(int i = 0 ; i < length; i++) {
			scoreResult += name.charAt(i) - 'A' + 1;
		}
		testOutput();
	}

	private static void testOutput() {
		System.out.println(scoreResult);
	}
}

class InputException extends RuntimeException {

	private static final long serialVersionUID = -2241080873269531985L;
	
	public InputException() {
		// TODO Auto-generated constructor stub
	}

	public InputException(String string) {
		// TODO Auto-generated constructor stub
	}
	
}