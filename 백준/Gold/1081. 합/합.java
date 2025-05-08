import java.io.*;
import java.util.*;

public class Main {

    static long L, U;

    static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Long.parseLong(st.nextToken());
        U = Long.parseLong(st.nextToken());
        ans = 0;
        ans = sum(U) - sum(L - 1);
        System.out.println(ans);
    }

    private static long sum(long num) {
        if (num <= 0) return 0;
        long[] c = new long[10];
        long s = 1, t, r;
        long total = 0;
        while (num > 0) {
            t = num / (s * 10);
            r = num % (s * 10);
            for (int i = 0; i < 10; i++) {
                c[i] += (long) t * s;
            }
            for (int i = 1; i <= r / s; i++) {
                c[i] += s;
            }
            c[(int) ((r / s + 1) % 10)] += r % s;
            num -= 9 * s;
            s *= 10;
        }
        for (int i = 1; i < 10; i++) {
            total += i * c[i];
        }
        return total;
    }
}
