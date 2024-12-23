import java.util.*;

public class Main {

    static long[][][][] dp = new long[11][102][102][102];
    static long[] fac = new long[11];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 트리의 레벨 수
        int a = sc.nextInt(); // 빨강 장난감 수
        int b = sc.nextInt(); // 초록 장난감 수
        int c = sc.nextInt(); // 파랑 장난감 수

        dp[0][0][0][0] = 1; // 초기 상태
        fac[0] = 1;

        // 팩토리얼 계산
        for (int i = 1; i <= 10; i++) {
            fac[i] = fac[i - 1] * i;
        }

        int base = 0;
        for (int i = 1; i <= n; i++) {
            base += i - 1;
            for (int j = 0; j <= a; j++) {
                for (int k = 0; k <= b; k++) {
                    for (int g = 0; g <= c; g++) {
                        if (base != (j + k + g)) continue;

                        if (i <= a - j) {
                            dp[i][j + i][k][g] += dp[i - 1][j][k][g];
                        }
                        if (i <= b - k) {
                            dp[i][j][k + i][g] += dp[i - 1][j][k][g];
                        }
                        if (i <= c - g) {
                            dp[i][j][k][g + i] += dp[i - 1][j][k][g];
                        }

                        if (i % 2 == 0 && i != 0) {
                            long temp = fac[i] / (fac[i / 2] * fac[i / 2]);
                            if (i / 2 <= a - j && i / 2 <= b - k) {
                                dp[i][j + i / 2][k + i / 2][g] += dp[i - 1][j][k][g] * temp;
                            }

                            if (i / 2 <= a - j && i / 2 <= c - g) {
                                dp[i][j + i / 2][k][g + i / 2] += dp[i - 1][j][k][g] * temp;
                            }

                            if (i / 2 <= b - k && i / 2 <= c - g) {
                                dp[i][j][k + i / 2][g + i / 2] += dp[i - 1][j][k][g] * temp;
                            }
                        }

                        if (i % 3 == 0 && i != 0) {
                            long temp = fac[i] / (fac[i / 3] * fac[i / 3] * fac[i / 3]);
                            if (i / 3 <= a - j && i / 3 <= b - k && i / 3 <= c - g) {
                                dp[i][j + i / 3][k + i / 3][g + i / 3] += dp[i - 1][j][k][g] * temp;
                            }
                        }
                    }
                }
            }
        }

        long ans = 0;
        for (int j = 0; j <= a; j++) {
            for (int k = 0; k <= b; k++) {
                for (int g = 0; g <= c; g++) {
                    ans += dp[n][j][k][g];
                }
            }
        }

        System.out.println(ans);
    }
}