import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static int N, M;

    static int result;

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
        M = sc.nextInt();
    }

    private static void testProcess() {
        result = (N-1) * (M-1) * 2;
    }

    private static void testOutput() {
        System.out.println(result);
    }
}
