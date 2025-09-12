import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static List<Integer> islandInfoList = new ArrayList<>();
    static final int ISLAND_UNVISITED = 0, ISLAND_VISITED = -1, ISLAND_WORK_DONE = -2, SEA_UNVISITED = -3, SEA_VISITED = -4;
    static final int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    static final int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 2][M + 2];
        for(int[] row : map) {
            Arrays.fill(row, SEA_UNVISITED); //미방문 바다
        }
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = line.charAt(j - 1) == 'x' ? ISLAND_UNVISITED : SEA_UNVISITED;
            }
        }
        int maxHeight = searchOcean(0, 0);
        if(islandInfoList.isEmpty()) { //내부에 섬 없음
            System.out.println(-1);
            return;
        }
        int[] cnts = new int[maxHeight + 1];
        for(int num : islandInfoList) {
            cnts[num]++;
        }
        for(int cnt : cnts) ans.append(cnt).append(" ");
        ans.setLength(ans.length() - 1);
        System.out.println(ans);
    }

    //해당 바다 내에 얼마나 섬이 있는지 전부 체크(다른 섬에 포함된 섬들을 자동으로 제외)
    private static int searchOcean(int startR, int startC) {
        Queue<int[]> ocean = new LinkedList<>();
        ocean.add(new int[]{startR, startC});
        map[startR][startC] = SEA_VISITED;
        Queue<int[]> island = new LinkedList<>();
        while (!ocean.isEmpty()) {
            int[] cur = ocean.poll();
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(inValid(nr, nc)) {
                    if(map[nr][nc] == SEA_UNVISITED) {
                        map[nr][nc] = SEA_VISITED; //바다 방문 처리
                        ocean.add(new int[]{nr, nc});
                    } else if(map[nr][nc] == ISLAND_UNVISITED) {
                        //바다와 직접 접한 모든 섬은 바로 체크(추가로 누군가에게 포함될 가능성 X)
                        island.add(new int[]{nr, nc});
                    }
                }
            }
        }
        
        return searchIsland(island);
    }

    //섬을 체크, 섬 내부에 여러 바다가 있다면 각각의 바다에 대해
    //searchOcean을 할 것이며 그 중 최고 깊이를 구하겠다.
    private static int searchIsland(Queue<int[]> island) {
        //섬이 비었다면 -1 출력
        if(island.isEmpty()) return -1;
        int maxDepth = 0;
        while(!island.isEmpty()) {
            int[] cur = island.poll();
            //방문한 섬이면 건너뛰기
            if(map[cur[0]][cur[1]] != ISLAND_UNVISITED) continue;
            islandVisit(cur); //섬 방문
            int nowHeight = boarderSearch(cur);
            islandInfoList.add(nowHeight); //해당 섬의 높이
            maxDepth = Math.max(maxDepth, nowHeight);
        }
        //내부 섬 중 가장 높은 높이로
        return maxDepth;
    }

    //섬을 따라 돌면서 섬 전체 범위 확정
    private static void islandVisit(int[] cur) {
        Queue<int[]> nowIsland = new LinkedList<>();
        nowIsland.add(cur);
        map[cur[0]][cur[1]] = ISLAND_VISITED;
        while(!nowIsland.isEmpty()) {
            int[] curI = nowIsland.poll();
            for(int d = 0; d < 8; d++) { //대각선 까지 방문하면서 섬부터 체크
                int nr = curI[0] + dr[d];
                int nc = curI[1] + dc[d];
                if(inValid(nr, nc) && map[nr][nc] == ISLAND_UNVISITED) {
                    map[nr][nc] = ISLAND_VISITED;
                    nowIsland.add(new int[]{nr, nc});
                }
            }
        }
    }

    //섬 경계면을 따라 돌면서 미방문한 바다 탐색
    private static int boarderSearch(int[] start) {
        Queue<int[]> boarderSearch = new LinkedList<>();
        boarderSearch.add(start);
        map[start[0]][start[1]] = ISLAND_WORK_DONE;
        int nowIslandHeight = 0;
        while(!boarderSearch.isEmpty()) {
            int[] cur = boarderSearch.poll();
            for(int d = 0; d < 8; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(inValid(nr, nc)) {
                    if(map[nr][nc] == ISLAND_VISITED) {
                        map[nr][nc] = ISLAND_WORK_DONE; //모든 체크가 끝난 섬
                        boarderSearch.add(new int[]{nr, nc});
                    } else if(map[nr][nc] == SEA_UNVISITED){
                        //미방문된 바다 발견시 즉시 탐색, 바다 방문 처리 겸
                        //해당 바다 내 가장 높은 섬 높이 +1 없다면 -1 반환 후 +1 에서 0 처리
                        nowIslandHeight = Math.max(nowIslandHeight, searchOcean(nr, nc) + 1);
                    }
                }
            }
        }
        return nowIslandHeight;
    }

    //유효성 검사
    private static boolean inValid(int r, int c) {
        return r >= 0 && r < N + 2 && c >= 0 && c < M + 2;
    }



}






