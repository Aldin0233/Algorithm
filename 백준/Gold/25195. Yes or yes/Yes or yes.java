import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, S;
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static boolean[] visited;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map.get(u).add(v);
        }
        S = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < S; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;
        while(!q.isEmpty()) {
            int node = q.poll();
            if(set.contains(node)) {
                continue;
            }
            if(map.get(node).isEmpty()) {
                System.out.println("yes");
                return;
            }
            for(int v : map.get(node)) {
                if(!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
        System.out.println("Yes");
    }

}



