import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] map;
    static int[] player;
    static String order;
    static final int[] dr = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static final int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                char c = str.charAt(j);
                if(c == 'R') {
                    map[i][j] += 1;
                } else if(c == 'I'){
                    player = new int[]{i, j};
                }
            }
        }
        order = br.readLine();

        for(int idx = 0; idx < order.length(); idx++) {
            movePlayer(order.charAt(idx) - '0' - 1);
            if(map[player[0]][player[1]] == 1 || crazyTriedCatch()) {
                System.out.printf("kraj %d", idx + 1);
                return;
            }
        }

        ansBuild();

        System.out.println(ans);
    }

    private static void ansBuild() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(player[0] == i && player[1] == j) {
                    ans.append('I');
                } else if(map[i][j] == 0) {
                    ans.append('.');
                } else {
                    ans.append('R');
                }
            }
            ans.append('\n');
        }
    }

    private static void movePlayer(int idx) {
        player[0] += dr[idx];
        player[1] += dc[idx];
    }

    private static boolean crazyTriedCatch() {
        int[][] crazyVisited = new int[R][C];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == 1) {
                    int r = i, c = j;
                    if(r > player[0]) {
                        r--;
                    } else if(r < player[0]) {
                        r++;
                    }
                    if(c > player[1]) {
                        c--;
                    } else if(c < player[1]) {
                        c++;
                    }
                    crazyVisited[r][c] += 1;
                    if(r == player[0] && c == player[1]) {
                        return true;
                    }
                }
            }
        }
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(crazyVisited[i][j] > 1) {
                    crazyVisited[i][j] = 0;
                }
            }
        }
        map = crazyVisited;
        return false;
    }


}

