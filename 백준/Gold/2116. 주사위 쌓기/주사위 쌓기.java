import java.io.*;
import java.util.*;

public class Main {

    public static int N, ans;
    public static int[][] diceInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        diceInfo = new int[N][6];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 6; j++) {
                diceInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0 ; i < 6; i++) {
            search(0, i, 0);
        }
        System.out.println(ans);
    }

    private static void search(int idx, int nowBottom, int sum) {
        int nowTop = getPairIdx(nowBottom);
        int tmp = 0;
        for(int i = 0 ; i < 6; i++) {
            if(i == nowBottom || i == nowTop) continue;
            tmp = Math.max(tmp, diceInfo[idx][i]);
        }
        if(idx == N - 1) {
            ans = Math.max(ans, sum + tmp);
            return;
        }
        for(int i = 0 ; i < 6; i++) {
            if(diceInfo[idx + 1][i] == diceInfo[idx][nowTop]) {
                search(idx + 1, i, sum + tmp);
                return;
            }
        }
    }

    private static int getPairIdx(int idx) {
        switch (idx) {
            case 0:
                return 5;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 0;
        }
        return -1;
    }

}





