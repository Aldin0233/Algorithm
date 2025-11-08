import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for(int i = 0; i < R; i++) {
            String c = br.readLine();
            for(int j = 1; j < C - 2; j++) {
                if(c.charAt(C - j - 1) != '.') {
                    pq.offer(new int[]{c.charAt(C - j - 1) - '1', j});
                    break;
                }
            }
        }
        int[] ranks = new int[9];
        int prevBestDistance = -1;
        int nowRank = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(prevBestDistance != cur[1]) {
                prevBestDistance = cur[1];
                nowRank++;
            }
            ranks[cur[0]] = nowRank;
        }
        for(int playerRank : ranks) {
            ans.append(playerRank).append('\n');
        }
        System.out.println(ans);
    }

}