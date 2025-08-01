import java.io.*;
import java.util.*;

public class Main {

    static int N, M, D;
    static boolean[][] enemyPosInfo;
    static int[] comb = new int[3];
    static int[] dr = {0, -1, 0};
    static int[] dc = {-1, 0, 1}; //왼쪽 적 우선
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        enemyPosInfo = new boolean[N][M];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++) {
                enemyPosInfo[i][j] = st.nextToken().equals("1");
            }
        }

        ans = 0;
        makeComb(0, 0);

        System.out.print(ans);
    }

    private static void makeComb(int idx, int selectedCnt) {
        if(selectedCnt >= 3) {
            simulation();
            return;
        }
        for(int i = idx; i < M; i++) {
            comb[selectedCnt] = i;
            makeComb(i + 1, selectedCnt + 1);
        }
    }

    private static void simulation() {
        boolean[][] curEnemyMap = copyEnemyPosInfo();
        int cnt = 0;
        boolean end = false;
        while(!end) {
            boolean[][] searchedEnemies = searchEnemy(curEnemyMap);
            MovedState nowMove = enemyMoveExceptedEliminate(curEnemyMap, searchedEnemies);
            cnt += nowMove.cnt;
            end = nowMove.end;
        }
        ans = Math.max(cnt, ans);
    }

    private static MovedState enemyMoveExceptedEliminate(boolean[][] curEnemyMap, boolean[][] searchedEnemies) {
        int cnt = 0;
        boolean end = true;
        for(int i = N - 1; i >= 0; i--) { //밑에서부터 먼저 내려가게 처리
            for(int j = 0; j < M; j++) {
                if(searchedEnemies[i][j]) { //제거된 적
                    curEnemyMap[i][j] = false;
                    cnt++;
                    continue;
                }
                if(curEnemyMap[i][j]) { //적 이동
                    curEnemyMap[i][j] = false;
                    if(i != N - 1) { //성 닿은 적은 제외
                        end = false;
                        curEnemyMap[i + 1][j] = true;
                    }
                }
            }
        }
        return new MovedState(cnt, end);
    }

    private static boolean[][] searchEnemy(boolean[][] curEnemyMap) {
        boolean[][] searchedEnemies = new boolean[N][M];
        for(int i = 0; i < 3; i++) { //3명의 궁수가
            Queue<int[]> qu = new LinkedList<>();
            qu.add(new int[]{N, comb[i]});
            boolean[][] visited = new boolean[N][M]; //BFS 방문 배열
            enemySearch: for(int j = 0; j < D; j++) { //D 거리만큼 BFS
                int size = qu.size();
                for(int k = 0; k < size; k++) {
                    int[] cur = qu.poll();
                    for(int d = 0; d < 3; d++) { //왼쪽부터 반원으로
                        int nx = cur[0] + dr[d];
                        int ny = cur[1] + dc[d];
                        if(inValid(nx, ny) && !visited[nx][ny]) { //적 있으면 탐색 중지
                            visited[nx][ny] = true;
                            if(curEnemyMap[nx][ny]) {
                                searchedEnemies[nx][ny] = true;
                                break enemySearch;
                            }
                            qu.add(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
        return searchedEnemies;
    }

    private static boolean inValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static boolean[][] copyEnemyPosInfo() {
        boolean[][] copy = new boolean[N][];
        for(int i = 0 ; i < N ; i++) {
            copy[i] = Arrays.copyOf(enemyPosInfo[i], M);
        }
        return copy;
    }



}

class MovedState {
    int cnt;
    boolean end;
    public MovedState(int cnt, boolean end) {
        this.cnt = cnt;
        this.end = end;
    }
}
