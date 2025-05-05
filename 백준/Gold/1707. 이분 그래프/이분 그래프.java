
import java.io.*;
import java.util.*;

public class Main {

    static int T, V, E;
    static Map<Integer, List<Integer>> map;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            map = new HashMap<>();
            for(int i = 0; i < V; i++) {
                map.put(i, new ArrayList<>());
            }
            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                map.get(a).add(b);
                map.get(b).add(a);
            }
            ans.append(bfs()).append("\n");
        }

        System.out.println(ans);
    }

    static String bfs() {
        int[] colors = new int[V];
        Arrays.fill(colors, -1);
        for(int i = 0 ; i < V; i++) {
            if(colors[i] == -1) {
                if(!bfs(i, colors)) {
                    return "NO";
                }
            }
        }
        return "YES";
    }

    static boolean bfs(int i, int[] colors) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next : map.get(cur)) {
                if(colors[next] == -1) {
                    colors[next] = 1 - colors[cur];
                    q.add(next);
                } else if(colors[next] == colors[cur]) {
                    return false;
                }
            }
        }
        return true;
    }



}
