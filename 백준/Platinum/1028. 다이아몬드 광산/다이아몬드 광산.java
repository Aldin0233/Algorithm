
import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static String[] map;
    //왼쪽 아래로 내려가는 대각선, 오른쪽 아래로 내려가는 대각선
    static int[][] prefixSumLd, prefixSumRd;
    static boolean[][] visited;
    static final int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new String[R];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine();
        }
        prefixSumLd = new int[R][C];
        prefixSumRd = new int[R][C];
        //대각선을 미리 계산
        for(int i = R - 1; i >= 0; i--) { //아래로 내려가는 대각선을 셀것이기 때문에 아래서부터 누적
            for(int j = 0; j < C; j++) {
                if(map[i].charAt(j) == '1') {
                    prefixSumLd[i][j] = 1;
                    prefixSumRd[i][j] = 1;
                    if(i + 1 == R) continue;
                    if(j > 0) prefixSumLd[i][j] += prefixSumLd[i + 1][j - 1];
                    if(j < C - 1) prefixSumRd[i][j] += prefixSumRd[i + 1][j + 1];
                }
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                int maxSize = Math.min(prefixSumLd[i][j], prefixSumRd[i][j]);
                for(int size = maxSize; size > ans; size--) {
                    if(canDiamond(i, j, size)) {
                        ans = size;
                        break; //해당 좌표에서 최댓값 찾음
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean canDiamond(int i, int j, int size) {
        int offset = size - 1; //오프셋만큼 간 곳은 무조건 존재 범위확인 X
        int mr = i + offset; //다이아몬드 중앙 행 //밑변 시작
        int lc = j - offset;
        int rc = j + offset;
        //밑변 검사, size 만큼 아래로 내려가서 왼쪽과 오른쪽 검사
        //Rd가 왼쪽 아래 밑변, Ld가 오른쪽 아래 밑변
        return prefixSumRd[mr][lc] >= size && prefixSumLd[mr][rc] >= size;
    }



}



