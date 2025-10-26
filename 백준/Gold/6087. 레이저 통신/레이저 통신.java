import java.io.*;
import java.util.*;

public class Main {

    static int W, H;
    static String[] field;
    static int[][][] visited;
    static final int[] dr = new int[] {-1, 1, 0, 0};
    static final int[] dc = new int[] {0, 0, -1, 1};
    static int[][] cPos = new int[2][2];
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        field = new String[H];
        visited = new int[H][W][4];
        int cIdx = 0;
        for (int i = 0; i < H; i++) {
            field[i] = br.readLine();
            for (int j = 0; j < W; j++) {
                if(field[i].charAt(j) == 'C') {
                    cPos[cIdx][0] = i;
                    cPos[cIdx][1] = j;
                    cIdx++;
                }
            }
        }
        for(int[][] field : visited) for(int[] direction : field) Arrays.fill(direction, Integer.MAX_VALUE);
        bfs();
        int result = Integer.MAX_VALUE;
        for(int d = 0; d < 4; d++) {
            result = Math.min(visited[cPos[1][0]][cPos[1][1]][d], result);
        }
        ans.append(result);
        System.out.println(ans);
    }

    private static void bfs() {
        Deque<int[]> dq = new ArrayDeque<>();
        int stR = cPos[0][0];
        int stC = cPos[0][1];
        for(int d = 0; d < 4; d++) {
            //r, c, 지금까지 꺾은 횟수, 현재 방향
            dq.add(new int[]{stR, stC, d});
            visited[stR][stC][d] = 0;
        }
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            int r = cur[0], c = cur[1], curD = cur[2];
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(isValid(nr, nc) && field[nr].charAt(nc) != '*') {
                    int nCnt = visited[r][c][curD] + (curD == d ? 0 : 1);
                    if(visited[nr][nc][d] > nCnt) {
                        visited[nr][nc][d] = nCnt;
                        //안 꺾인 직선 우선 탐색
                        if(curD == d) dq.addFirst(new int[]{nr, nc, d});
                        else dq.addLast(new int[]{nr, nc, d});
                    }
                }
            }
        }

    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }




}