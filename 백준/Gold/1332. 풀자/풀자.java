import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, V;
    static int[] interests;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        interests = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            interests[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][][] visited = new boolean[N][1001][1001];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, interests[0], interests[0], 1});
        visited[0][interests[0]][interests[0]] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0] == N - 1) continue;

            for(int jump = 1; jump <= 2; jump++) {
                int next = cur[0] + jump;
                if(next >= N) continue;

                int nextMin = Math.min(cur[1], interests[next]);
                int nextMax = Math.max(cur[2], interests[next]);
                int nextSolCnt = cur[3] + 1;

                if(nextMax - nextMin >= V) {
                    System.out.println(nextSolCnt);
                    return;
                }

                if(!visited[next][nextMin][nextMax]) {
                    visited[next][nextMin][nextMax] = true;
                    q.offer(new int[]{next, nextMin, nextMax, nextSolCnt});
                }

            }
        }
        System.out.println(N);
    }

}

