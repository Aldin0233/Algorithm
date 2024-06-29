import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static int N, R, C;
	
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
		N = sc.nextInt(); R = sc.nextInt(); C = sc.nextInt();
	}

	private static void testProcess() {
        long size = (long) Math.pow(2, N);
        int r = R; int c = C;
        
        while (size > 1) {
            size /= 2;
            int quad = 0;
            //각 어느 분면에 위치했는지 탐색
            if (r < size && c < size) {
                quad = 0;
            } else if (r < size && c >= size) {
                quad = 1;
                c -= size;
            } else if (r >= size && c < size) {
                quad = 2;
                r -= size;
            } else {
                quad = 3;
                r -= size;
                c -= size;
            }
            //현재 분면까지 얼마나 드는지
            result += quad * size * size;
        }
    }


	private static void testOutput() {
		System.out.println(result);
	}
}