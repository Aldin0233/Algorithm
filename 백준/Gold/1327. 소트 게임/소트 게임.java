
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static Set<String> visited = new HashSet<>();
    static String destination;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) sb.append(st.nextToken());
        String start = sb.toString();
        sb.setLength(0);
        sb = new StringBuilder();
        for(int i = 1; i <= N; i++) sb.append(i);
        destination = sb.toString();
        ans = bfs(start);
        System.out.println(ans);
    }

    private static int bfs(String start) {
        Queue<String> q = new LinkedList<>();
        q.add(start);
        visited.add(start);
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                String cur = q.poll();
                if(cur.equals(destination)) return cnt;
                for(int j = 0; j <= N - K; j++) {
                    String next = reverseAtoB(j, j + K - 1, cur);
                    if(visited.add(next)) q.add(next);
                }
            }
            cnt++;
        }
        return -1;
    }

    private static String reverseAtoB(int a, int b, String str) {
        char[] arr = str.toCharArray();
        while(a < b) {
            char tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
            a++;
            b--;
        }
        return new String(arr);
    }


}





