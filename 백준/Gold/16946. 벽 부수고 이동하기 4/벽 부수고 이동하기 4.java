import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static int[][] pointToStrUnion;
    static List<Integer> zeroToSize =  new ArrayList<>();
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        pointToStrUnion = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        bfs();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) {
                    int size = 1;
                    boolean[] visited  = new boolean[zeroToSize.size()];
                    for(int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(inMap(nx, ny) && map[nx][ny] == 0) {
                            int idx = pointToStrUnion[nx][ny];
                            if(!visited[idx]) {
                                visited[idx] = true;
                                size += zeroToSize.get(idx);
                            }
                        }
                    }
                    result.append(size % 10);
                } else {
                    result.append(0);
                }
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    public static void bfs() {
        boolean[][] visited = new boolean[N][M];
        int idx = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!visited[i][j] && map[i][j] == 0) {
                    idx++;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    int size = 0;
                    while(!q.isEmpty()) {
                        int[] cur = q.poll();
                        if(visited[cur[0]][cur[1]]) continue;
                        visited[cur[0]][cur[1]] = true;
                        pointToStrUnion[cur[0]][cur[1]] = idx;
                        size++;
                        for(int d = 0; d < 4; d++) {
                            int nx = cur[0] + dx[d];
                            int ny = cur[1] + dy[d];
                            if(inMap(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny]) q.add(new int[]{nx, ny});
                        }
                    }
                    zeroToSize.add(size);
                }
            }
        }
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }



}