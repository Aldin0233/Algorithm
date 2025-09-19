import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static Node[] tree;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new Node[N];
        for(int i = 0; i < N; i++) {
            tree[i] = new Node();
        }
        StringTokenizer st;
        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char t = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken()) - 1;
            tree[i].isSheep = t == 'S';
            tree[i].cnt = a;
            tree[p].children.add(tree[i]);
        }
        ans.append(dfs(tree[0]));
        System.out.println(ans);
    }

    private static long dfs(Node node) {
        long sheepCnt = node.isSheep ? node.cnt : 0;
        for(Node child : node.children) {
            sheepCnt += dfs(child);
        }
        return Math.max(node.isSheep ? sheepCnt : sheepCnt - node.cnt, 0);
    }

}

class Node {
    boolean isSheep;
    int cnt;
    List<Node> children = new ArrayList<>();

}







