import java.io.*;
import java.util.*;

public class Main {

    static int H, W;
    static int[] walls;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        walls = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            walls[i] = Integer.parseInt(st.nextToken());
        }

        int[] leftHighWall = new int[W];
        int[] rightHighWall = new int[W];
        leftHighWall[0] = walls[0];
        rightHighWall[W - 1] = walls[W - 1];
        for (int i = 1; i < W; i++) {
            leftHighWall[i] = Math.max(leftHighWall[i - 1], walls[i]);
            rightHighWall[(W - 1) - i] = Math.max(rightHighWall[(W - 1) - i + 1], walls[(W - 1) - i]);
        }

        for (int i = 0; i < W; i++) {
            int minHeight = Math.min(leftHighWall[i], rightHighWall[i]);
            if(minHeight > walls[i]) {
                ans += minHeight - walls[i];
            }
        }

        System.out.println(ans);

    }

}

