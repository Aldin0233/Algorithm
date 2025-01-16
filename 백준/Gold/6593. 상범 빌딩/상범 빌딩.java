import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int L, R, C;
    private static int startPointX, startPointY, startPointZ;
    private static String[][] map;
    private static boolean[][][] visited;
    private static StringBuilder answer = new StringBuilder();
    private static int[] dx = {0, 0, 0, 0, 1, -1};
    private static int[] dy = {-1, 1, 0, 0, 0, 0};
    private static int[] dz = {0, 0, -1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        while(L != 0 && R != 0 && C != 0) {
            map = new String[L][R];
            visited = new boolean[L][R][C];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    map[i][j] = br.readLine();
                    for(int k = 0; k < C; k++){
                        if(map[i][j].charAt(k) == 'S') {
                            startPointX = i;
                            startPointY = j;
                            startPointZ = k;
                        }
                    }
                }
                br.readLine();
            }
            bfs();
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }
        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startPointX, startPointY, startPointZ, 0));
        while(!q.isEmpty()) {
            Point p = q.poll();
            if(visited[p.x][p.y][p.z]) {
                continue;
            }
            visited[p.x][p.y][p.z] = true;
            if(map[p.x][p.y].charAt(p.z) == 'E') {
                answer.append("Escaped in ").append(p.time).append(" minute(s).\n");
                return;
            }
            for(int d = 0; d < 6; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                int nz = p.z + dz[d];
                if(inMap(nx, ny, nz) && canGo(nx, ny, nz)) {
                    q.add(new Point(nx,ny, nz, p.time+1));
                }
            }

        }
        answer.append("Trapped!\n");

    }

    private static boolean inMap(int x, int y, int z) {
        return x >= 0 && x < L && y >= 0 && y < R && z >= 0 && z < C;
    }

    private static boolean canGo(int x, int y, int z) {
        return (map[x][y].charAt(z) == '.' || map[x][y].charAt(z) == 'E') && !visited[x][y][z];
    }

    static class Point {
        int x, y, z;
        int time;
        Point(int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }
}