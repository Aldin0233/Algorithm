import java.io.*;
import java.util.*;


public class Main {

    static int N, M, Q;
    static int[] P;
    static Map<Integer, List<Edge>> edges;
    static Map<String, int[]> dps;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        edges = new HashMap<>();
        dps = new HashMap<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = st.nextToken().charAt(0) - 'A';
            edges.computeIfAbsent(c, k -> new ArrayList<>()).add(new Edge(a, b));
        }
        makeDpComb(new int[5], new boolean[5], 0);
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[5];
            for(int j = 0; j < 5; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            String key = convertArrToKey(arr);
            int[] dp = dps.get(key);
            long sum = 0;
            for(int j = 0; j < 5; j++) {
                sum += ((long)dp[j]) * arr[j];
            }
            ans.append(sum).append("\n");
        }
        ans.setLength(ans.length() - 1);
        System.out.println(ans);
    }

    private static void makeDpComb(int[] list, boolean[] visited, int curIdx) {
        if(curIdx == 5) {
            calDpComb(list);
            return;
        }
        for(int i = 0; i < list.length; i++) {
            if(!visited[i]) {
                list[curIdx] = i;
                visited[i] = true;
                makeDpComb(list, visited, curIdx + 1);
                visited[i] = false;
            }
        }
    }

    private static void calDpComb(int[] list) {
        StringBuilder Key = new StringBuilder();
        int[] edgeCnts = new int[5];
        make();
        for(int cur : list) {
            Key.append(cur);
            for(Edge nowEdge : edges.getOrDefault(cur, new ArrayList<>())) {
                int refA = find(nowEdge.a);
                int refB = find(nowEdge.b);
                if(refA != refB) {
                    edgeCnts[cur]++;
                    union(refA, refB);
                }
            }
        }
        dps.put(Key.toString(), edgeCnts);
    }

    private static String convertArrToKey(int[] arr) {
        int n = arr.length;
        Integer[] idx = new Integer[5];
        for(int i = 0; i < 5; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, Comparator.comparingInt(i -> arr[i]));
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 5; i++) {
            sb.append(idx[i]);
        }
        return sb.toString();
    }

    private static void make() {
        P = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            P[i] = i;
        }
    }

    private static int find(int x) {
        if(P[x] == x) return x;
        return P[x] = find(P[x]);
    }

    private static void union(int a, int b) {
        P[a] = b;
    }



}

class Edge {
    int a, b;
    public Edge(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
