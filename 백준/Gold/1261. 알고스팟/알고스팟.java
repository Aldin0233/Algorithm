
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int ansBreakCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(ansBreakCount);
    }

    private static void bfs() {
        boolean[][] visited = new boolean[N][M];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        pq.offer(new int[]{0, 0, 0});
        visited[0][0] = true;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[0] == N - 1 && cur[1] == M - 1) {
                ansBreakCount = cur[2];
                return;
            }
            for(int d = 0; d < 4; d++) {
                int nx = cur[0] + dr[d];
                int ny = cur[1] + dc[d];
                if(inValid(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if(map[nx][ny] == 1) {
                        pq.offer(new int[]{nx, ny, cur[2] + 1});
                    } else {
                        pq.offer(new int[]{nx, ny, cur[2]});
                    }
                }
            }
        }
    }

    private static boolean inValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }


}

