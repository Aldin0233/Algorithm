import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int N, M;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            long cnt = 0;
            for(int i = N; i <= M; i++) {
                cnt += zeroCnt(i);
            }
            ans.append(cnt).append("\n");
        }

        System.out.println(ans);
    }

    static long zeroCnt(int n) {
        if(n == 0) return 1;
        long cnt = 0;
        while(n > 0) {
            if(n % 10 == 0) cnt++;
            n /= 10;
        }
        return cnt;
    }

}
