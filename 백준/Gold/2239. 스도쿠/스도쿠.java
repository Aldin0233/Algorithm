import java.io.*;

public class Main {

    static final int SDOKU_SIZE = 9;
    static int[][] sdoku;
    static boolean[][] visited;
    static StringBuilder ans = new StringBuilder(); //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sdoku = new int[SDOKU_SIZE][SDOKU_SIZE];
        visited = new boolean[SDOKU_SIZE * 3][SDOKU_SIZE];

        for(int i = 0; i < SDOKU_SIZE; i++) {
            String line = br.readLine();
            for(int j = 0; j < SDOKU_SIZE; j++) {
                sdoku[i][j] = line.charAt(j) - '1';
                if(sdoku[i][j] != -1) {
                    visited[i][sdoku[i][j]] = true;
                    visited[j + 9][sdoku[i][j]] = true;
                    visited[calPartition(i, j) + 18][sdoku[i][j]] = true;
                }
            }
        }

        dfs(0, 0);

        for(int i = 0; i < SDOKU_SIZE; i++) {
            for(int j = 0; j < SDOKU_SIZE; j++) {
                ans.append(sdoku[i][j] + 1);
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }

    private static boolean dfs(int nowX, int nowY) {
        if(nowX >= SDOKU_SIZE) {
            return true;
        }
        if(sdoku[nowX][nowY] != -1) {
            return dfs(nowY < 8 ? nowX : nowX + 1, nowY < 8 ? nowY + 1 : 0);
        }

        for(int i = 0; i < SDOKU_SIZE; i++) {
            if(cantVisit(nowX, nowY, i)) continue;

            checkVisit(nowX, nowY, i);
            sdoku[nowX][nowY] = i;
            if(dfs(nowY < 8 ? nowX : nowX + 1, nowY < 8 ? nowY + 1 : 0)) {
                return true;
            }
            checkVisit(nowX, nowY, i);
        }
        sdoku[nowX][nowY] = -1;
        return false;
    }

    private static void checkVisit(int nowX, int nowY, int i) {
        visited[nowX][i] = !visited[nowX][i];
        visited[nowY + 9][i] = !visited[nowY + 9][i];
        visited[calPartition(nowX, nowY) + 18][i] = !visited[calPartition(nowX, nowY) + 18][i];
    }

    private static boolean cantVisit(int nowX, int nowY, int i) {
        return visited[nowX][i] || visited[nowY + 9][i] || visited[calPartition(nowX, nowY) + 18][i];
    }

    private static int calPartition(int x, int y) {
        return ((x / 3) * 3) + (y / 3);
    }

}
