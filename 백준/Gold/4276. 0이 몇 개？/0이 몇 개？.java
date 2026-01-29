import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    //정수 범위가 제한 없다.. BigInteger 써야 하나 고민
    static long N, M;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        while(N >= 0) {
            long min = Math.min(N, M);
            long max = Math.max(N, M);
            long result = countZeroOneToUp(max) - countZeroOneToUp(min - 1);
            ans.append(result).append("\n");

            st = new StringTokenizer(br.readLine());
            N = Long.parseLong(st.nextToken());
            M = Long.parseLong(st.nextToken());
        }

        System.out.println(ans);

    }

    static long countZeroOneToUp(long n) {
        if(n < 0) return 0L;

        long cnt = 0L;

        for(long factor = 1; factor <= n; factor *= 10) {
            //구간의 앞 블럭
            long higher = n / (factor * 10);
            long cur = (n / factor) % 10;
            //뒷 블럭
            long lower = n % factor;

            if(higher == 0) continue; //제일 윗블럭

            if(cur == 0) cnt += (higher - 1) * factor + (lower + 1);
            else cnt += higher * factor;
        }
        return cnt + 1;
    }

}
