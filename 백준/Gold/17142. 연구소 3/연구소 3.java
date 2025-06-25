

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static Map<Integer, int[]> virusList = new HashMap<>();

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virusList.put(idx++, new int[]{i, j});
            }
        }

        dfs(0, 0, new int[M]);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void dfs(int idx, int last, int[] list) {
        if(idx >= M) {
            reproductionVirus(list);
            return;
        }
        for(int i = last; i < virusList.size(); i++) {
            list[idx] = i;
            dfs(idx + 1, i + 1, list);
        }
    }

    private static void reproductionVirus(int[] list) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        for(int key : list) {
            q.offer(virusList.get(key));
            visited[virusList.get(key)[0]][virusList.get(key)[1]] = true;
        }
        int time = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            boolean reproduction = false;
            if(checkVirusReproduction(visited)) break;
            for(int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for(int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(inValid(nr, nc) && !visited[nr][nc]) {
                        reproduction = true;
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
            if(reproduction) {
                time++;
            }
        }

        if(!checkVirusReproduction(visited)) {
            return;
        }
        ans = Math.min(ans, time);
    }

    private static boolean inValid(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != 1;
    }

    private static boolean checkVirusReproduction(boolean[][] visited) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


}


