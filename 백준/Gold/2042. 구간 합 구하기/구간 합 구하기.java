import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        SegTree tree = new SegTree(arr);
        for(int i = 0 ; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());
            if(a == 1) {
                tree.update(0, N - 1, b, c, 1);
            } else {
                ans.append(tree.query(0, N - 1, b, (int) c - 1, 1)).append("\n");
            }
        }
        System.out.println(ans);
    }


}

class SegTree {
    long[] tree;
    int N;

    public SegTree(long[] arr) {
        this.N = arr.length;
        tree = new long[N * 4];
        buildTree(arr, 0, N - 1, 1);
    }

    private void buildTree(long[] arr, int start, int end, int node) {
        if(start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        buildTree(arr, start, mid, 2 * node);
        buildTree(arr, mid + 1, end, 2 * node + 1);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    public long query(int start, int end, int left, int right, int node) {
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return query(start, mid, left, right, 2 * node) +
                query(mid + 1, end, left, right, 2 * node + 1);
    }

    void update(int start, int end, int idx, long val, int node) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (idx <= mid) update(start, mid, idx, val, 2 * node);
        else update(mid + 1, end, idx, val, 2 * node + 1);

        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

}
