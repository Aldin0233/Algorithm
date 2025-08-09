import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static boolean[][] visited;
    static final int[] dr = {-1, 0, 1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력 받고 맵 전처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                visited[i][j] = str.charAt(j) == 'x'; //건물 있는 곳은 방문 처리를 미리 해서 못 접근하게 함
            }
        }
        ans = 0;
        for(int i = 0; i < R; i++) {
            if(dfs(i, 0)) ans++; //그리디하게 접근 위에서부터 접근 시도
        }

        System.out.println(ans);
    }

    private static boolean dfs(int r, int c) {
        visited[r][c] = true; //어차피 이 경로로 가는 게 가능하다면 체크된 경로가 됨, 불가능하면 방문해도 상관 없음
        if(c == C - 1) return true;
        int nc = c + 1;
        for(int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            if(isValid(nr) && !visited[nr][nc]) {
                if(dfs(nr, nc)) return true;
            }
        }
        return false;
    }

    private static boolean isValid(int r) {
        return r >= 0 && r < R;
    }



}

