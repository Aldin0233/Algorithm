import java.io.*;
import java.util.*;

public class Main {

    static long N;

    static StringBuilder ans = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        long[] arr = sum(N);
        for(int i = 0; i < 10; i++) {
            ans.append(arr[i]).append(" ");
        }
        System.out.println(ans);
    }

    private static long[] sum(long num) {
        long[] c = new long[10];
        long s = 1, t, r;
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
        return c;
    }
}
