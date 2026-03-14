import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int targetA, targetB;
    static List<Integer>[] edges;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        targetA = Integer.parseInt(st.nextToken());
        targetB = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        //양방향이였네용
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        if(targetA == targetB) {
            System.out.println(0);
            return;
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(targetA);
        boolean[] visited = new boolean[N + 1];
        visited[targetA] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int u = q.poll();
                for (int v : edges[u]) {
                    if (!visited[v]) {
                        if(v == targetB) {
                            System.out.println(cnt + 1);
                            return;
                        }
                        visited[v] = true;
                        q.offer(v);
                    }
                }
            }
            cnt++;
        }
        System.out.println(-1);
    }

}

