import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[][] cheeseMap;
    static boolean[][] air;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheeseMap = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                cheeseMap[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        ans = 0;
        
        while(true) {
            air = airBfs();
            boolean cheese = false;
            Queue<int[]> melted = new LinkedList<>();
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(cheeseMap[i][j]) {
                        cheese = true;
                        int cnt = 0;
                        for(int d = 0; d < 4; d++) if(air[i + dr[d]][j + dc[d]]) cnt++;
                        if(cnt >= 2) melted.add(new int[]{i, j});
                    }
                }
            }
            if(!cheese) break;
            meltDown(melted);
            ans++;
        }

        System.out.println(ans);
    }

    private static void meltDown(Queue<int[]> melted) {
        while(!melted.isEmpty()) {
            int[] cur = melted.poll();
            cheeseMap[cur[0]][cur[1]] = false;
        }
    }

    private static boolean[][] airBfs() {
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(inValid(nr, nc) && !visited[nr][nc] && !cheeseMap[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
            }
        }
        return visited;
    }

    private static boolean inValid(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }


}



