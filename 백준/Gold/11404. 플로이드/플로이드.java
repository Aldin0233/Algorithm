import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] busCost;
    static int[][] finalCost;
    static StringBuilder ans = new StringBuilder(); //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        busCost = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(busCost[i], Integer.MAX_VALUE);
        }
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            busCost[a][b] = Math.min(busCost[a][b] , c);
        }

        finalCost = new int[N][N];
        for(int i = 0; i < N; i++) {
            busStart(i);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                ans.append(finalCost[i][j]).append(" ");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }

    static void busStart(int idx) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        boolean[] visited = new boolean[N];
        pq.add(new int[]{idx, 0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(visited[cur[0]]) continue;
            visited[cur[0]] = true;
            finalCost[idx][cur[0]] = cur[1];
            for(int i = 0; i < N; i++) {
                if(cur[0] == i || visited[i]) continue;
                if(busCost[cur[0]][i] == Integer.MAX_VALUE) continue;
                pq.add(new int[]{i, cur[1] + busCost[cur[0]][i]});
            }
        }
    }



}

