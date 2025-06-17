

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int R, C, T;
    static int[][] room;
    static int[] airPurifier = new int[2];

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] == -1) {
                    airPurifier[airPurifier[0] == 0 ? 0 : 1] = i;
                }
            }
        }
        
        for(int t = 0; t < T; t++) {
            if(!diffusion()) break; //먼지 없음
            purified();
        }
        ans = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(room[i][j] != -1) {
                    ans += room[i][j];
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean diffusion() { //미세먼지 확산
        int[][] diffusion = new int[R][C];
        boolean find = false;
        //동시에 미세먼지 확산
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(room[i][j] > 0) {
                    find = true;
                    int diff = room[i][j] / 5;
                    for(int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(inValid(nr, nc)) {
                            diffusion[nr][nc] += diff;
                            room[i][j] -= diff;
                        }
                    }
                }
            }
        }
        //확산된 먼지 추가
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                room[i][j] += diffusion[i][j];
            }
        }
        return find;
    }

    private static void purified() { //공기 청정기 작동
        int top = airPurifier[0];
        int bottom = airPurifier[1];

        //0열 처리
        for(int i = top - 1; i > 0; i--) room[i][0] = room[i - 1][0];
        for(int i = bottom + 1; i < R - 1; i++) room[i][0] = room[i + 1][0];

        //최상단 및 최하단 행
        for(int j = 0; j < C - 1; j++) {
            room[0][j] = room[0][j + 1];
            room[R - 1][j] = room[R - 1][j + 1];
        }

        //C - 1열 처리
        for(int i = 0; i < top; i++) room[i][C - 1] = room[i + 1][C - 1];
        for(int i = R - 1; i > bottom; i--) room[i][C - 1] = room[i - 1][C - 1];

        //공기청정기 행 처리
        for(int j = C - 1; j > 1; j--) {
            room[top][j] = room[top][j - 1];
            room[bottom][j] = room[bottom][j - 1];
        }

        room[top][1] = room[bottom][1] = 0;

    }

    private static boolean inValid(int nr, int nc) { //방 밖 혹은 공기청정기 여부 확인
        return nr >= 0 && nr < R && nc >= 0 && nc < C && room[nr][nc] != -1;
    }


}

