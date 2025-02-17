import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Captain captain = new Captain(N, K);
        captain.globalSearch();
    }

}

class Captain {
    final int[] dr = {-1, 0, 1, 0};
    final int[] dc = {0, -1, 0, 1};
    BufferedReader br;
    int N, K;
    int sunkCnt;
    boolean[][] map;
    Queue<int[]> hitQueue;

    public Captain(int N, int K) {
        this.N = N;
        this.K = K;
        br = new BufferedReader(new InputStreamReader(System.in));
        sunkCnt = 0;
        map = new boolean[N + 1][N + 1];
        hitQueue = new LinkedList<>();
    }

    private boolean attackSuccess() {
        return sunkCnt >= K;
    }

    public void globalSearch() throws IOException {
        for(int i = 1 ; i <= N ; i ++) {
            for(int j = (i % 5) == 0 ? 5 : (i % 5); j <= N; j += 5) {
                if(map[i][j]) {
                    continue;
                }
                map[i][j] = true;
                System.out.printf("%d %d\n", i, j);
                System.out.flush();
                String response = br.readLine();
                if(("hit").equals(response)) {
                    hitQueue.add(new int[] {i, j});
                    localSearch();
                }
                if(attackSuccess()) {
                    return;
                }
            }
        }
    }

    private void localSearch() throws IOException {
        while(!hitQueue.isEmpty()) {
            int[] xy = hitQueue.poll();
            for(int d = 0; d < 4; d++) {
                int nr = xy[0] + dr[d];
                int nc = xy[1] + dc[d];
                if(inMap(nr, nc) && !map[nr][nc]) {
                    map[nr][nc] = true;
                    System.out.printf("%d %d\n", nr, nc);
                    System.out.flush();
                    String response = br.readLine();
                    if("sunk".equals(response)) {
                        sunkCnt++;
                        if(attackSuccess()) {
                            return;
                        }
                        hitQueue.add(new int[] {nr, nc});
                    } else if("hit".equals(response)) {
                        hitQueue.add(new int[] {nr, nc});
                    }
                }
            }
        }
    }

    private boolean inMap(int x, int y) {
        return x > 0 && x <= N && y > 0 && y <= N;
    }

}
