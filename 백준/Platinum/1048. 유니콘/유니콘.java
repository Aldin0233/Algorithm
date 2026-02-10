import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, L;
    static String target;
    static char[][] field;
    static long[][] prefixSum;
    static long[][] dp;

    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        target = br.readLine();
        field = new char[N][M];
        dp = new long[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                field[i][j] = line.charAt(j);
                if(field[i][j] == target.charAt(0)) {dp[i][j] = 1;}
            }
        }
        buildPrefixSum();
        for(int i = 1; i < target.length(); i++) {
            buildPrefixSum();
            findWayNextChar(target.charAt(i));
        }

        System.out.println(sumAllDp());
    }

    static long sumAllDp() {
        long sum = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sum += dp[i][j];
                sum %= MOD;
            }
        }
        return sum;
    }

    static void findWayNextChar(char c) {
        long[][] ndp = new long[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j] != c) {
                    ndp[i][j] = 0;
                    continue;
                }
                ndp[i][j] = calCanMoveToHere(i, j);
            }
        }
        dp = ndp;
    }

    static long calCanMoveToHere(int r, int c) {
        long sum = 0;
        //해당 좌표로 오기 위해 직선과, 근처 를 제외하고 나면 4개의 사각형이 나옴(모서리는 밑에서 제거)
        sum += calRectSum(0, 0, r - 2, c - 2);
        sum += calRectSum(0, c + 2, r - 2, M - 1);
        sum += calRectSum(r + 2, 0, N - 1, c - 2);
        sum += calRectSum(r + 2, c + 2, N - 1, M - 1);
        sum %= MOD;

        long sub = 0;
        sub += getDpWithValidTest(r - 2, c - 2);
        sub += getDpWithValidTest(r + 2, c - 2);
        sub += getDpWithValidTest(r - 2, c + 2);
        sub += getDpWithValidTest(r + 2, c + 2);
        sub %= MOD;

        long res = (sum - sub) % MOD;
        if(res < 0) res += MOD;
        return res;
    }

    static long calRectSum(int r1, int c1, int r2, int c2) {
        if(r1 > r2 || c1 > c2) return 0;
        if(r1 >= N || c1 >= M || r2 < 0 || c2 < 0) return 0;

        r1 = Math.max(r1, 0);
        c1 = Math.max(c1, 0);
        r2 = Math.min(r2, N - 1);
        c2 = Math.min(c2, M - 1);

        long res = prefixSum[r2 + 1][c2 + 1]
                - prefixSum[r1][c2 + 1]
                - prefixSum[r2 + 1][c1]
                + prefixSum[r1][c1];
        res %= MOD;
        if(res < 0) res += MOD;
        return res;
    }

    static long getDpWithValidTest(int r, int c) {
        if(r < 0 || r >= N || c < 0 || c >= M) return 0;
        return dp[r][c];
    }

    static void buildPrefixSum() {
        prefixSum = new long[N + 1][M + 1];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                long val = prefixSum[i][j + 1] + prefixSum[i + 1][j] - prefixSum[i][j] + dp[i][j];
                val %= MOD;
                if(val < 0) val += MOD;
                prefixSum[i + 1][j + 1] = val;
            }
        }
    }


}

