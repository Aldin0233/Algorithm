import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int N, M;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] hackCount;

    static StringBuilder result;

    public static void main(String[] args) throws IOException {
        test();
    }

    private static void test() throws IOException {
        setting();
        testSettingByInput();
        testProcessingByInput();
        testResultToOutput();
    }

    private static void setting() {
        br = new BufferedReader(new InputStreamReader(System.in));
        result = new StringBuilder();
    }

    private static void testSettingByInput() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        testSetting();
    }

    private static void testSetting() {
        graph = new ArrayList[N + 1];
        hackCount = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private static void testProcessingByInput() throws IOException {
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[B].add(A);
        }

        int maxHack = 0;

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            hackCount[i] = bfs(i);
            maxHack = Math.max(maxHack, hackCount[i]);
        }

        for (int i = 1; i <= N; i++) {
            if (hackCount[i] == maxHack) {
                result.append(i).append(" ");
            }
        }
    }

    private static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int adj : graph[node]) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    queue.offer(adj);
                    count++;
                }
            }
        }

        return count;
    }

    private static void testResultToOutput() {
        System.out.println(result.toString().trim());
    }

}
