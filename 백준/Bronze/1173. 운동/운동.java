import java.io.*;
import java.util.*;

public class Main {

    static int N, m, M, T, R;
    static int nowHeartRate;

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        if(m + T > M) {
            System.out.println(-1);
            return;
        }
        int timeCnt = 0;
        int totalTimeCnt = 0;
        nowHeartRate = m;
        while(timeCnt < N) {
            if(nowHeartRate + T <= M) {
                timeCnt++;
                nowHeartRate += T;
            } else {
                nowHeartRate = Math.max(nowHeartRate - R, m);
            }
            totalTimeCnt++;

        }
        System.out.println(totalTimeCnt);
    }



}
