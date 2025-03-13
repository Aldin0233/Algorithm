import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] map;
    static Queue<int[]> moveQ = new LinkedList<>();
    static Queue<int[]> fireQ = new LinkedList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    moveQ.add(new int[]{i, j, 0});
                } else if (map[i][j] == 'F') {
                    fireQ.add(new int[]{i, j});
                }
            }
        }

        int ans = bfs();
        System.out.println(ans != -1 ? ans : "IMPOSSIBLE");
    }

    private static int bfs() {
        while (!moveQ.isEmpty()) {
            int fireSize = fireQ.size();
            for (int i = 0; i < fireSize; i++) {
                int[] fire = fireQ.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = fire[0] + dc[d];
                    int ny = fire[1] + dr[d];

                    if (!inMap(nx, ny)) continue;

                    if (map[nx][ny] == '.' || map[nx][ny] == 'J') {
                        map[nx][ny] = 'F';
                        fireQ.add(new int[]{nx, ny});
                    }
                }
            }

            int moveSize = moveQ.size();
            for (int i = 0; i < moveSize; i++) {
                int[] move = moveQ.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = move[0] + dc[d];
                    int ny = move[1] + dr[d];

                    if (!inMap(nx, ny)) return move[2] + 1;

                    if (map[nx][ny] == '.') {
                        map[nx][ny] = 'J';
                        moveQ.add(new int[]{nx, ny, move[2] + 1});
                    }
                }
            }
        }
        return -1;
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}