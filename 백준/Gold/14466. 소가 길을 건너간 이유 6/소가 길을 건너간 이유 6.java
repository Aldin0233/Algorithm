
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, R;
    static State[][] farm;
    static boolean[][] visited, cowPos;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static List<Integer> cows = new ArrayList<>();
    static int ans;

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        farm = new State[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                farm[i][j] = new State();
            }
        }
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;
            makeRoad(r1, c1, r2, c2);
        }

        visited = new boolean[N][N];
        cowPos = new boolean[N][N];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            cowPos[r1][c1] = true;
        }

        ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    int cowCnt = bfs(i, j);
                    if(cowCnt != 0) {
                        cows.add(cowCnt);
                    }
                }
            }
        }
        
        if(cows.size() <= 1) {
            ans = 0;
        } else {
            int total = K * (K - 1) / 2; //전체 쌍의 수
            int sameArea = 0; //같은 구역에서 만들어지는 쌍의 수
            for (Integer cow : cows) {
                sameArea += cow * (cow - 1) / 2;
            }
            ans = total - sameArea;
        }

        System.out.println(ans);
    }

    private static int bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        int cowCnt = 0;
        q.add(new int[]{i, j});
        visited[i][j] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cowPos[cur[0]][cur[1]]) {
                cowCnt++;
            }
            State curState = farm[cur[0]][cur[1]];
            for(int d = 0; d < 4; d++) {
                if(!curState.d[d]) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(isValid(nr, nc) && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
        return cowCnt;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static void makeRoad(int r1, int c1, int r2, int c2) {
        State one = farm[r1][c1];
        State two = farm[r2][c2];
        if(r1 == r2) {
            if(c1 > c2) { //one이 오른쪽에 위치
                one.d[2] = true;
                two.d[3] = true;
            } else {
                one.d[3] = true;
                two.d[2] = true;
            }
        } else {
            if(r1 > r2) { //one이 아랫쪽에 위치
                one.d[0] = true;
                two.d[1] = true;
            } else {
                one.d[1] = true;
                two.d[0] = true;
            }
        }
    }

}

class State {
    //up, down, left, right;
    boolean[] d = new boolean[4];

}








