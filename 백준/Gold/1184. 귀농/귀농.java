import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] field;
    static long[][] prefixSum;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        field = new int[N][N];
        prefixSum = new long[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                prefixSum[i + 1][j + 1] = field[i][j]
                 + prefixSum[i][j + 1] + prefixSum[i + 1][j]
                - prefixSum[i][j];
            }
        }
        long ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                ans += leftUpCase(i, j);
                ans += rightUpCase(i, j);
            }
        }
        System.out.println(ans);

    }

    static long leftUpCase(int i, int j) {
        int Alen = i * j;
        int Blen = (N - i) * (N - j);

        if(Alen == 0 || Blen == 0) return 0;

        long[] A = new long[Alen];
        long[] B = new long[Blen];

        int idx = 0;
        for(int x = 0; x <= i - 1; x++) {
            for(int y = 0; y <= j - 1; y++) {
                A[idx++] = calSize(x, i - 1, y, j - 1);
            }
        }

        idx = 0;
        for(int x = i; x < N; x++) {
            for(int y = j; y < N; y++) {
                B[idx++] = calSize(i, x, j, y);
            }
        }

        return calCaseCnt(A, B);
    }

    static long rightUpCase(int i, int j) {
        int Alen = i * (N - j);
        int Blen = (N - i) * j;

        if(Alen == 0 || Blen == 0) return 0;

        long[] A = new long[Alen];
        long[] B = new long[Blen];

        int idx = 0;
        for(int x = 0; x <= i - 1; x++) {
            for(int y = j; y < N; y++) {
                A[idx++] = calSize(x, i - 1, j, y);
            }
        }

        idx = 0;
        for(int x = i; x < N; x++) {
            for(int y = 0; y <= j - 1; y++) {
                B[idx++] = calSize(i, x, y, j - 1);
            }
        }

        return calCaseCnt(A, B);
    }

    static long calCaseCnt(long[] A, long[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        long cnt = 0;
        int p = 0, q = 0;
        while(p < A.length && q < B.length) {
            if(A[p] < B[q]) p++;
            else if(A[p] > B[q]) q++;
            else {
                long v = A[p];
                long c1 = 0, c2 = 0;
                while(p < A.length && A[p] == v) { c1++; p++; }
                while(q < B.length && B[q] == v) { c2++; q++; }
                cnt += c1 * c2;
            }
        }
        return cnt;
    }

    static long calSize(int x1, int x2, int y1, int y2) {
        return prefixSum[x2 + 1][y2 + 1]
                - prefixSum[x1][y2 + 1]
                - prefixSum[x2 + 1][y1]
                + prefixSum[x1][y1];
    }

}

