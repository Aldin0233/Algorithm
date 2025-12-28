import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static int[] testPapers;
    static long ans;
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        testPapers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            testPapers[i] = Integer.parseInt(st.nextToken());
        }
        ans = 0;
        int lo = 0;
        int hi = 20 * N;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if(canMakeGroup(mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean canMakeGroup(int mid) {
        int sum = 0;
        int groupCnt = 0;
        for(int x : testPapers) {
            sum += x;
            if(sum >= mid) {
                groupCnt++;
                sum = 0;
                if(groupCnt >= K) return true;
            }
        }
        return groupCnt >= K;
    }





}



