
import java.io.*;
import java.util.*;

public class Main {

    static boolean[][] paperInfo = new boolean[10][10];
    static int[] colorPaper = {0, 5, 5, 5, 5, 5}; //남은 색종이 수
    static int oneLeft; //1이 남은 횟수
    static int ans = 26; //모든 종이를 다 썼을때 보다 1 큰 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 10; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++){
                paperInfo[i][j] = Integer.parseInt(st.nextToken()) == 1;
                if(paperInfo[i][j]) oneLeft++;
            }
        }

        dfs(0);

        System.out.println(ans == 26 ? -1: ans);
    }

    private static void dfs(int used) {
        if(used >= ans) return;
        if(oneLeft == 0) { //다 덮음
            ans = used; //위에서 used가 ans보다 큰 것을 가지쳤기 때문에 무조건 작음
            return;
        }
        int[] nextPos = findNext();
        for(int size = 5; size > 0; size--){
            if(colorPaper[size] <= 0) continue;
            if(!canPlace(nextPos, size)) continue;
            place(nextPos, size, false);
            colorPaper[size]--;
            oneLeft -= size * size;

            dfs(used + 1);

            oneLeft += size * size;
            colorPaper[size]++;
            place(nextPos, size, true);

        }
    }

    private static boolean canPlace(int[] pos, int size){
        if(pos[0] + size > 10 || pos[1] + size > 10) return false; //종이 벗어남
        for(int r = pos[0]; r < pos[0] + size; r++){
            for(int c = pos[1]; c < pos[1] + size; c++){
                if(!paperInfo[r][c]) return false;
            }
        }
        return true;
    }

    private static void place(int[] pos, int size, boolean info) {
        for(int r = pos[0]; r < pos[0] + size; r++){
            for(int c = pos[1]; c < pos[1] + size; c++){
                paperInfo[r][c] = info;
            }
        }
    }

    private static int[] findNext() {
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(paperInfo[i][j]) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1}; //불가능
    }

}



