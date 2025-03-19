import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        Point shark = null;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Point(i, j, 0);
                    map[i][j] = 0;
                }
            }
        }

        bfs(shark);

        System.out.println(shark.dist);
    }

    private static void bfs(Point shark) {
        int size = 2, eat = 0;
        while(true) {
            Point eatable = findFish(shark.x, shark.y, size);

            if(eatable == null) break;
            map[eatable.x][eatable.y] = 0;
            eat++;
            if(eat == size) {
                size++;
                eat = 0;
            }

            shark.x = eatable.x;
            shark.y = eatable.y;
            shark.dist += eatable.dist;
        }
    }

    private static Point findFish(int x, int y, int size) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Point(x, y, 0));
        Point fish = null;
        boolean find = false;
        while(!q.isEmpty()) {
            int qSize = q.size();
            for(int i = 0; i < qSize; i++) {
                Point cur = q.poll();
                if(map[cur.x][cur.y] > 0 && map[cur.x][cur.y] < size) {
                    if(fish == null) {
                        fish = cur;
                    } else {
                        fish = compare(fish, cur);
                    }
                    find = true;
                }
                for(int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if(inMap(nx, ny) && map[nx][ny] <= size && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny, cur.dist + 1));
                    }
                }
            }
            if(find) {
                break;
            }
        }
        return fish;
    }

    private static Point compare(Point a, Point b) {
        if(a.x == b.x) {
            if(a.y < b.y) {
                return a;
            } else {
                return b;
            }
        } else {
            if(a.x < b.x) {
                return a;
            } else {
                return b;
            }
        }
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}

class Point {
    int x, y, dist;
    Point(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}
