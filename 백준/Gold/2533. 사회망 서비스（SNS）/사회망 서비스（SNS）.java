import java.util.*;

public class Main {

    static int N;
    static ArrayList<Integer>[] tree;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        
        tree = new ArrayList[N + 1];
        dp = new int[N + 1][2]; 
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        dfs(1, -1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
    
    // DFS로 트리를 탐색하면서 DP를 계산합니다.
    public static void dfs(int node, int parent) {
        dp[node][0] = 0; 
        dp[node][1] = 1; 

        for (int child : tree[node]) {
            if (child == parent) continue; 

            dfs(child, node);

            // 만약 현재 노드를 얼리 어답터로 선택하지 않으면, 자식 노드는 반드시 얼리 어답터여야 함
            dp[node][0] += dp[child][1];

            // 만약 현재 노드를 얼리 어답터로 선택하면, 자식 노드는 얼리 어답터가 아니어도 됨
            dp[node][1] += Math.min(dp[child][0], dp[child][1]);
        }
    }
}