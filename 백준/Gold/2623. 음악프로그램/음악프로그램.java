

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static int[] inDegree;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        inDegree = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int[] order = new int[count];
            for(int j = 0; j < count; j++) {
                order[j] = Integer.parseInt(st.nextToken());
            }
            for(int j = 0; j < count - 1; j++) {
                int from = order[j];
                int to = order[j + 1];
                graph[from].add(to);
                inDegree[to]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);
            for(int next : graph[cur]) {
                inDegree[next]--;
                if(inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        if(result.size() < N) {
            ans.append(0);
        } else {
            for(int num : result) {
                ans.append(num).append("\n");
            }
        }

        System.out.println(ans);
    }


}


