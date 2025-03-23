import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int testCase = 0;
        StringBuilder sb = new StringBuilder();
        do {
            int[][] map = new int[N][N];
            int ans = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            PriorityQueue<Point> pq = new PriorityQueue<>();
            pq.add(new Point(0, 0, map[0][0]));
            boolean[][] visited = new boolean[N][N];

            while(!pq.isEmpty()) {
                Point p = pq.poll();
                if(visited[p.x][p.y]) continue;
                visited[p.x][p.y] = true;
                if(p.x == N - 1 && p.y == N - 1) {
                    ans = p.cost;
                    break;
                }
                for(int d = 0; d < 4; d++) {
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if(inMap(nx, ny) && !visited[nx][ny]) {
                        pq.add(new Point(nx, ny, p.cost + map[nx][ny]));
                    }
                }
            }
            sb.append(String.format("Problem %d: %d\n", ++testCase, ans));
            N = Integer.parseInt(br.readLine());
        } while (N != 0);
        System.out.println(sb);
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}

class Point implements Comparable<Point> {
    int x, y, cost;
    public Point(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    public int compareTo(Point o) {
        return cost - o.cost;
    }
}

