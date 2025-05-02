
import java.io.*;
import java.util.*;

public class Main {

    static int S;
    static boolean[][] visited;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        Search();
        System.out.println(ans);
    }

    private static void Search() {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[1500][1500];
        q.add(new int[]{0, 1, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int clipboard = cur[0];
            int nowText = cur[1];
            int time = cur[2];
            if(nowText == S) {
                ans = time;
                return;
            }
            if(clipboard > 0 && clipboard + nowText < 1500 && !visited[clipboard][nowText + clipboard]) {
                visited[clipboard][nowText] = true;
                q.add(new int[]{clipboard, nowText + clipboard, time + 1});
            }
            if(!visited[nowText][nowText]) {
                visited[nowText][nowText] = true;
                q.add(new int[]{nowText, nowText, time + 1});
            }
            if(nowText > 1 && !visited[clipboard][nowText - 1]) {
                visited[clipboard][nowText - 1] = true;
                q.add(new int[]{clipboard, nowText - 1, time + 1});
            }
        }

    }



}
