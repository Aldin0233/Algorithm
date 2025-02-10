import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] map;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        check: while(true) {
            ans++;
            boolean melted = false;
            for(int i = 0 ; i < N; i++) {
                for(int j = 0 ; j < M; j++) {
                    if(map[i][j] > 0) {
                        melted = true;
                        for(int d = 0; d < 4; d++) {
                            int ni = i + dr[d];
                            int nj = j + dc[d];
                            if(inMap(ni, nj) && map[ni][nj] == 0) {
                                map[i][j]--;
                            }
                            if(map[i][j] <= 0) {
                                map[i][j] = -1;
                                break;
                            }
                        }
                    }
                }
            }
            if(!melted) {
                ans = 0;
                break check;
            }
            boolean[][] visited = new boolean[N][M];
            boolean unChecked = false;
            for(int i = 0 ; i < N; i++) {
                for(int j = 0 ; j < M; j++) {
                    if(map[i][j] == -1) {
                        map[i][j] = 0;
                    } else if(map[i][j] > 0 && !visited[i][j]) {
                        if(!unChecked) {
                            bfs(i, j, visited);
                            unChecked = true;
                        } else {
                            break check;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static void bfs(int i, int j, boolean[][] visited) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        while(!q.isEmpty()) {
            Point p = q.poll();
            if(visited[p.x][p.y]) {
                continue;
            }
            visited[p.x][p.y] = true;
            for(int d = 0; d < 4; d++) {
                int nx = p.x + dr[d];
                int ny = p.y + dc[d];
                if(inMap(nx, ny) && map[nx][ny] > 0 && !visited[nx][ny]) {
                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    private static boolean inMap(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M;
    }

}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
