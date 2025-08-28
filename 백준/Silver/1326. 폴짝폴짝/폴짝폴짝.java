import java.io.*;
import java.util.*;

public class Main {

    static int N, a, b;
    static int[] map;
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken()) - 1;
        b = Integer.parseInt(st.nextToken()) - 1;
        Queue<Integer> qu = new LinkedList<>();
        qu.add(a);
        visited[a] = true;
        ans = 0;
        while(!qu.isEmpty()) {
            int qSize = qu.size();
            for(int i = 0; i < qSize; i++) {
                int cur = qu.poll();
                if(cur == b) {
                    System.out.println(ans);
                    return;
                }
                for(int j = 1; j * map[cur] < N; j++) {
                    int jump = j * map[cur];
                    int down = cur - jump;
                    int up = cur + jump;
                    if(down >= 0 && !visited[down]) {
                        visited[down] = true;
                        qu.add(down);
                    }
                    if(up < N && !visited[up]) {
                        visited[up] = true;
                        qu.add(up);
                    }
                    if(up >= N && down < 0) {
                        break;
                    }
                }
            }
            ans++;
        }
        System.out.println(-1);

    }

}


