import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static int N;
    static long X;
    static long[][] dp;

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
        X = sc.nextLong();
    }

    private static void testProcess() {
        dp = new long[N+1][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for(int i = 1; i<=N; i++){
            dp[i][0] = (dp[i-1][0] * 2) + 1;
            dp[i][1] = (dp[i-1][1] * 2) + 3;
        }

        result = findHam(N);
    }

    private static long findHam(int n) {
        if(n == 0) {
            return 1;
        }
        
        if(X <= n) {
            return 0;
        }

        long tmp = 0;
        if(X >= dp[n][1] - n) {
            return dp[n][0];
        } else if( X >= (dp[n][1] / 2 + 1)) {
            tmp = (dp[n-1][0] + 1);
            X -= (dp[n-1][1] + 2);
        } else {
            X -= 1;
        }
        if(X > 0)  {
            tmp += findHam(n-1);
        }
        return tmp;
    }

    private static void testOutput() {
        System.out.println(result);
    }
}
