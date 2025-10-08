import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static Map<Integer, List<Integer>> edges = new HashMap<>();
    static int[] parents;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges.putIfAbsent(a, new ArrayList<>());
            edges.putIfAbsent(b, new ArrayList<>());
            edges.get(a).add(b);
            edges.get(b).add(a);
        }
        parents = new int[N + 1];
        Arrays.fill(parents, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        parents[1] = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next : edges.get(cur)) {
                if(parents[next] == -1) {
                    parents[next] = cur;
                    q.add(next);
                }    
            }
        }
        for(int i = 2; i <= N; i++) {
            ans.append(parents[i]).append("\n");
        }
        System.out.println(ans);
    }
}