import java.io.*;
import java.util.*;

public class Main {

    private static long N, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        int A, B, C, D;
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        ans = Long.MAX_VALUE;

        if (B * C < D * A) {
            calculate(C, D, A, B);
        } else {
            calculate(A, B, C, D);
        }


        System.out.println(ans);
    }

    private static void calculate(long A, long B, long C, long D) {
        for (int i = 0; i < C; ++i) {
            long index = (long) Math.ceil((double) (N - i * A) / C);
            if (index < 0) {
                break;
            }
            ans = Math.min(ans, i * B + index * D);
        }
    }

}
