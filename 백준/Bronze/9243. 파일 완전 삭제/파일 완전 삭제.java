import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int N;
	static String willDelete, deleted;
	
	static deleteStatus deleteResult;
	
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		testInput();
	}

	private static void testInput() {
		N = Integer.parseInt(sc.nextLine());
		willDelete = sc.nextLine().trim();
		deleted = sc.nextLine().trim();
		testProcess();
	}

	private static void testProcess() {
		deleteResult = deleteStatus.SUCCEEDED;
		process: {
			if(N % 2 == 0) {
				if(willDelete.equals(deleted)) {
					break process;
				} 
				deleteResult = deleteStatus.FAILED;
			} else {
				for(int i = 0 ; i<willDelete.length(); i++) {
					if(willDelete.charAt(i) == deleted.charAt(i)) {
						deleteResult = deleteStatus.FAILED;
						break process;
					}
				}
			}
		}
		testOutput();
	}
	
	private static void testOutput() {
		System.out.println(deleteResult.getMessage());
	}
}

enum deleteStatus {
    SUCCEEDED("Deletion succeeded"),
    FAILED("Deletion failed");

    private final String message;

    deleteStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}