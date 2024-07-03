import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static int[] arr;

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
        arr = new int[5];
        for(int i = 0; i < 5; i++) {
            arr[i] = sc.nextInt();
        }
    }

    private static void testProcess() {
        boolean find = false;
        while(!find){
            result++;
            int cnt = 0;
            for(int i = 0 ; i <5; i++){
                if(result % arr[i] == 0) cnt++;
            }
            if(cnt >= 3) {
                return;
            }
        }
    }

    private static void testOutput() {
        System.out.println(result);
    }
}