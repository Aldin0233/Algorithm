import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] immigrationDeskTimes;
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        immigrationDeskTimes = new int[N];
        int maxTime = 0;
        for(int i = 0 ; i < N ; i++) {
            immigrationDeskTimes[i] = Integer.parseInt(br.readLine());
            maxTime = Math.max(maxTime, immigrationDeskTimes[i]);
        }
        long left = 0;
        long right = (long)maxTime * M;
        ans = Long.MAX_VALUE;
        while(left <= right) {
            long mid = (left + right) / 2;
            if(canProcess(mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean canProcess(long mid) {
        long count = 0;
        for(int time : immigrationDeskTimes) {
            count += mid / time;
            if(count >= M) return true;
        }
        return false;
    }

}
