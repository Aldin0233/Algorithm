import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] speeds = new int[100][2];
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int idx = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int section = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            for(int j = idx; j < idx + section; j++) {
                speeds[j][0] = speed;
            }
            idx += section;
        }
        idx = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int section = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            for(int j = idx; j < idx + section; j++) {
                speeds[j][1] = speed;
            }
            idx += section;
        }

        ans = 0;
        for(int i = 0; i < 100; i++) {
            ans = Math.max(speeds[i][1] - speeds[i][0], ans);
        }

        System.out.println(ans);
    }

}
