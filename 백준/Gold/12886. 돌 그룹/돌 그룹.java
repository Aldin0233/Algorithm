import java.io.*;
import java.util.*;

public class Main {
    static int A, B, C;
    static boolean[][] visited = new boolean[1501][1501];
    static boolean can;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        can = false;
        bfs(A, B, C);
        System.out.println(can? 1 : 0);
    }

    public static void bfs(int a, int b, int c) {
        if((a + b + c) % 3 != 0) return;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, b, c});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            Arrays.sort(cur);
            if(visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;
            if(cur[0] == cur[1] && cur[1] == cur[2]) {
                can = true;
                return;
            }
            if(cur[0] == 0 || cur[1] == 0 || cur[2] == 0) continue;

            if(cur[0] != cur[1]) {
                q.add(process(cur[0], cur[1], cur[2]));
            }
            if(cur[0] != cur[2]) {
                q.add(process(cur[0], cur[2], cur[1]));
            }
            if(cur[1] != cur[2]) {
                q.add(process(cur[1], cur[2], cur[0]));
            }
        }
    }

    private static int[] process(int a, int b, int c) {
        int compareMax = Math.max(a, b);
        int compareMin = Math.min(a, b);
        return new int[] {compareMin + compareMin, compareMax - compareMin, c};
    }


}
