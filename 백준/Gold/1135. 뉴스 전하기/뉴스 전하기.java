import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        for (int i = 0; i < N; i++)
            nodes[i] = new Node(i);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1)
                nodes[parent].children.add(nodes[i]);
        }

        int result = dfs(nodes[0]);
        System.out.println(result);
    }

    static int dfs(Node node) {
        if (node.children.isEmpty()) return 0;

        List<Integer> childTimes = new ArrayList<>();
        for (Node child : node.children)
            childTimes.add(dfs(child));

        Collections.sort(childTimes, Collections.reverseOrder());

        int maxTime = 0;
        for (int i = 0; i < childTimes.size(); i++) {
            maxTime = Math.max(maxTime, childTimes.get(i) + i + 1);
        }

        return maxTime;
    }

}

class Node {
    int idx; //디버깅용 idx;
    List<Node> children;

    Node(int idx) {
        this.idx = idx;
        children = new ArrayList<>();
    }
}