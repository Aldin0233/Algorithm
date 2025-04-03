import java.io.*;
import java.util.*;

class Main {

    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        System.out.println(dfs(N, M));
    }

    private static int dfs(int n, int m) {
        if(n <= 1 && m <= 1) return 0;
        if(n > m) {
            return dfs(n / 2, m) + dfs(n - (n / 2), m) + 1;
        } else {
            return dfs(n, m / 2) + dfs(n, m - (m / 2)) + 1;
        }
    }
}