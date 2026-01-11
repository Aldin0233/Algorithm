import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long N;
    static int L;
    static long[] prefixSum;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        for(int k = L; k <= 100; k++) {
            long twoN = 2L * N;
            if(twoN % k != 0) continue; //정수에 안맞음
            // a는 시작점
            long q = twoN / k; // 2a + k - 1 이랑 같아야 함
            long t = q - (k - 1); // 2a 랑 같아야 함
            if(t % 2 != 0) continue; //따라서 짝수여야함
            long a = t / 2;
            if(a < 0) continue;

            for(int i = 0; i < k; i++) {
                ans.append(a + i).append(" ");
            }
            System.out.println(ans);
            return;
        }
        System.out.println(-1);
    }




}




