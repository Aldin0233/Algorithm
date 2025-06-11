
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] cheeseStorage;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int ansTime = 0, ansCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheeseStorage = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheeseStorage[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(ansTime);
        System.out.println(ansCount);
    }

    private static void bfs() {
        while(true) {
            int count = 0; //치즈 조각 및 찾았는지 여부
            boolean[][] visited = new boolean[N][M];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{0, 0}); //왼쪽 상단은 무조건 비어있음.
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                for(int d = 0; d < 4; d++) {
                    int nx = cur[0] + dr[d];
                    int ny = cur[1] + dc[d];
                    if(inValid(nx, ny) && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        if(cheeseStorage[nx][ny] == 1) {
                            cheeseStorage[nx][ny] = 0;
                            count++;
                        } else {
                            q.add(new int[]{nx, ny});
                        }
                    }
                }
            }
            if(count == 0) { //다 녹았음
                break;
            }
            ansTime++;
            ansCount = count;
        }
    }

    private static boolean inValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }


}

