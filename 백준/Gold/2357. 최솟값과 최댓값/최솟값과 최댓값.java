import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr;
    static Node[] tree;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        tree = new Node[N * 4];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        buildTree(1, 0, N - 1);
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; //인덱스 범위 갱신
            int b = Integer.parseInt(st.nextToken()) - 1; //인덱스 범위 갱신
            printNode(query(1, 0, N - 1, a, b)); //검색
        }

        System.out.println(ans);
    }

    private static void buildTree(int node, int start, int end) {
        if(start == end) { //제일 하단부 도착
            tree[node] = new Node(arr[start], arr[start]);
        } else {
            int mid = (start + end) / 2;
            buildTree(node * 2, start, mid);
            buildTree((node * 2) + 1, mid + 1, end);
            Node left = tree[node * 2];
            Node right = tree[node * 2 + 1];
            tree[node] = compareValue(left, right); //이 구간 내 최저값, 최댓값 갱신
        }
    }

    private static Node query(int node, int start, int end, int left, int right) {
        if(start > right || end < left) {
            return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE); //구간 벗어남
        }
        if(left <= start && end <= right) {
            return tree[node]; // 구간 내부
        }
        int mid = (start + end) / 2;
        Node leftResponse = query(node * 2, start, mid, left, right); //구간 특정
        Node rightResponse = query(node * 2 + 1, mid + 1, end, left, right);
        return compareValue(leftResponse, rightResponse); //갱신
    }

    private static Node compareValue(Node left, Node right) { //노드값 비교해서 최저값과 최대값 노드로 재생성
        return new Node(
                Math.min(left.minValue, right.minValue),
                Math.max(left.maxValue, right.maxValue)
        );
    }

    private static void printNode(Node node) {
        ans.append(node.minValue).append(" ").append(node.maxValue).append("\n");
    }

}

class Node {
    int minValue;
    int maxValue;

    Node(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
}



