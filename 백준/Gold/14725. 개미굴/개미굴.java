import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Node root = new Node("");
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            root.insert(st);
        }
        System.out.println(root.treeToString(0));
    }

}

class Node implements Comparable<Node> {
    String data;
    List<Node> list;
    public Node(String data) {
        this.data = data;
        list = new ArrayList<>();
    }

    public int compareTo(Node o) {
        return this.data.compareTo(o.data);
    }

    public void insert(StringTokenizer st) {
        if(!st.hasMoreTokens()) {
            return;
        }
        String data = st.nextToken();
        boolean find = false;
        for(Node n : this.list) {
            if(n.data.equals(data)) {
                find = true;
                n.insert(st);
            }
        }
        if(!find) {
            Node newNode = new Node(data);
            this.list.add(newNode);
            newNode.insert(st);
        }
    }

    public String treeToString(int depth) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(this.list);
        for(Node n : this.list) {
            sb.append("--".repeat(depth)).append(n.data).append("\n");
            sb.append(n.treeToString(depth+1));
        }
        return sb.toString();
    }
}

