import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //2453
    static int N;
    static String[] studentNo;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        studentNo = new String[N];
        for(int i = 0; i < N; i++) {
            studentNo[i] = br.readLine();
        }
        int stNoLen = studentNo[0].length();
        Trie t = new Trie(stNoLen);
        for(int i = 0; i < N; i++) {
            t.insert(studentNo[i]);
        }
        System.out.println(t.findNodeCountIsN(N));
    }



}

class Trie {
    Node root;
    int maxDepth;
    int[] nodeCountByDepth;
    public Trie(int maxDepth) {
        root = new Node();
        this.maxDepth = maxDepth;
        nodeCountByDepth = new int[maxDepth + 1];
    }

    public void insert(String no) {
        Node cur = root;
        for(int depth = 1; depth <= no.length(); depth++) {
            int nowIdx = maxDepth - depth;
            int nowNo = no.charAt(nowIdx) - '0';
            if(cur.child[nowNo] == null) {
                cur.child[nowNo] = new Node();
                nodeCountByDepth[depth]++;
            }
            cur = cur.child[nowNo];
        }
    }

    public int findNodeCountIsN(int N) {
        for(int k = 1; k <= maxDepth; k++) {
            if(nodeCountByDepth[k] == N) return k;
        }
        return -1;
    }
}

class Node {
    Node[] child;
    Node() {
        child = new Node[10];
    }
}




