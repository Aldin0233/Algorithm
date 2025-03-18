import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static boolean[] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            arr = new int[N];
            visited = new boolean[N];
            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    dfs(i, new ArrayList<>());
                }
            }

            sb.append(N - count).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int current, List<Integer> route) {
        if(visited[current]) {
            if(route.contains(current)) {
                count += countCycleSize(current, route);
            }
            return;
        }
        visited[current] = true;
        route.add(current);
        int next = arr[current];
        dfs(next, route);
    }

    private static int countCycleSize(int current, List<Integer> route) {
        for(int i = 0; i < route.size(); i++) {
            if(route.get(i) == current) {
                return route.size() - i;
            }
        }
        return 0;
    }

}
