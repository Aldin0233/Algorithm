import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static String[] textField;
    static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        textField = new String[N];
        for (int i = 0; i < N; i++) {
            textField[i] = br.readLine();
        }
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(textField[i].charAt(j) == 'F') {
                    for(int d = 0; d < 8; d++) {
                        cnt += check(i, j, d);
                    }
                }
            }
        }
        ans.append(cnt);
        System.out.println(ans);

    }

    private static int check(int i, int j, int d) {
        if(!isValid(i, j, d)) return 0;
        return textField[i + dr[d]].charAt(j + dc[d]) == 'O' && textField[i + dr[d] * 2].charAt(j + dc[d] * 2) == 'X'? 1 : 0;
    }

    private static boolean isValid(int i, int j, int d) {
        int limitI = i + dr[d] * 2;
        int limitJ = j + dc[d] * 2;
        return limitI >= 0 && limitJ >= 0 && limitI < N && limitJ < M;
    }

}

