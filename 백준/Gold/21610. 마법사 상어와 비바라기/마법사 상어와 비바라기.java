

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    static int[][] map;

    static final int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<int[]> clouds = new ArrayList<>();
        clouds.add(new int[]{N - 1, 0});
        clouds.add(new int[]{N - 1, 1});
        clouds.add(new int[]{N - 2, 0});
        clouds.add(new int[]{N - 2, 1});

        for(int o = 0; o < M; o++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int time = Integer.parseInt(st.nextToken()) % N;

            int drMove = dr[d] * time;
            int dcMove = dc[d] * time;

            boolean[][] visited = new boolean[N][N];

            for (int[] cloud : clouds) {
                int nr = calNowPos(cloud[0], drMove);
                int nc = calNowPos(cloud[1], dcMove);
                visited[nr][nc] = true;
                map[nr][nc]++;
            }
            List<int[]> newClouds = new ArrayList<>();

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(visited[i][j]) {
                        waterCopyBug(i, j);
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j] && map[i][j] >= 2) {
                        newClouds.add(new int[]{i, j});
                        map[i][j] -= 2;
                    }
                }
            }
            clouds = newClouds;

        }

        ans = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                ans += map[i][j];
            }
        }

        System.out.println(ans);
    }

    private static void waterCopyBug(int i, int j) {
        for(int d = 1; d <= 4; d++) {
            int nr = i + dr[(d * 2) - 1];
            int nc = j + dc[(d * 2) - 1];
            if(isValid(nr, nc) && map[nr][nc] > 0) {
                map[i][j]++;
            }
        }
    }

    private static boolean isValid(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    private static int calNowPos(int now, int move) {
        return (now + N + move) % N;
    }

}


