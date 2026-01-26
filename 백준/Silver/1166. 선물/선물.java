import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long N;
    static double L, W, H;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        L = Double.parseDouble(st.nextToken());
        W = Double.parseDouble(st.nextToken());
        H = Double.parseDouble(st.nextToken());
        double left = 0.0;
        double right = Math.min(L, Math.min(W, H));
        for(int trySearch = 0; trySearch < 400; trySearch++) {
            double mid = (left + right) / 2.0;
            if(can(mid)) left = mid;
            else right = mid;
        }
        System.out.println(left);
    }

    static boolean can(double a) {

        long x = (long) (L / a);
        long y = (long) (W / a);
        long z = (long) (H / a);

        long tmp = satMul(x, y, N);
        tmp = satMul(z, tmp, N);
        return tmp >= N;
    }

    static long satMul(long a, long b, long limit) {
        if(a == 0 || b == 0) return 0;
        if(a > limit / b) return limit;
        long p = a * b;
        return Math.min(p, limit);
    }










}




