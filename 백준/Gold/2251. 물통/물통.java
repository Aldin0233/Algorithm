import java.io.*;
import java.util.*;

public class Main {

    private static int A, B, C;
    private static boolean[] canC;
    private static boolean[][][] visited;
    private static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A  = Integer.parseInt(st.nextToken());
        B  = Integer.parseInt(st.nextToken());
        C  = Integer.parseInt(st.nextToken());
        canC = new boolean[C + 1];
        visited = new boolean[A + 1][B + 1][C + 1];
        dfs(0, 0, C);
        for(int i = 0 ; i <= C; i++) {
            if(canC[i]) ans.append(i).append(" ");
        }
        ans.setLength(ans.length()-1);
        System.out.println(ans);
    }

    private static void dfs(int a, int b, int c) {
        if (visited[a][b][c]) {
            return;
        }
        visited[a][b][c] = true;
        if(a == 0) {
            canC[c] = true;
        }
        int ab = a + b;
        int ac = a + c;
        int bc = b + c;
        if(ab > B) {
            dfs(ab- B, B, c);
        } else {
            dfs(0, ab, c);
        }
        if(ab > A) {
            dfs(A, ab - A, c);
        } else {
            dfs(ab, 0, c);
        }
        if(ac > C) {
            dfs(ac - C, b, C);
        } else {
            dfs(0, b, ac);
        }
        if(ac > A) {
            dfs(A, b, ac - A);
        } else {
            dfs(ac, b, 0);
        }
        if(bc > C) {
            dfs(a, bc - C, C);
        } else {
            dfs(a, 0, bc);
        }
        if(bc > B) {
            dfs(a, B, bc - B);
        } else {
            dfs(a, bc, 0);
        }



    }


}
