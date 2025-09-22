
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static List<Ruby> rubies;
    static boolean[][] visited;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        rubies = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int v = Integer.parseInt(st.nextToken());
                rubies.add(new Ruby(i, j, v));
            }
        }
        Collections.sort(rubies);
        visited = new boolean[N][M];
        dfs(0, 0, 0);

        System.out.println(ans);
    }

    private static void dfs(int idx, int select, int nowSum) {
        ans = Math.max(ans, nowSum);
        if(select >= K) return;
        for(int i = idx; i < rubies.size(); i++) {
            Ruby cur = rubies.get(i);
            //가지치기, 내림차순으로 정렬했기 때문에 이번 값이 최대
            if(nowSum + (cur.v * (K - select)) <= ans) break;
            if(!conflict(cur)) {
                visited[cur.r][cur.c] = true;
                dfs(i + 1, select + 1, nowSum + cur.v);
                visited[cur.r][cur.c] = false;
            }
        }
    }

    //주변 충돌 여부 점검
    private static boolean conflict(Ruby cur) {
        for(int d = 0; d < 4; d++) {
            int nr = cur.r + dr[d];
            int nc = cur.c + dc[d];
            if(inValid(nr, nc) && visited[nr][nc]) return true;
        }
        return false;
    }

    //좌표 유효성 검사
    private static boolean inValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

}

class Ruby implements Comparable<Ruby> {
    int r, c; //좌표
    int v; //가치

    Ruby(int r, int c, int v) {
        this.r = r;
        this.c = c;
        this.v = v;
    }

    public int compareTo(Ruby o) {
        return o.v - this.v;
    }

}









