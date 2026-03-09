import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int R, C;
    static String[] map;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        br.readLine();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new String[R];
            for(int i = 0; i < R; i++) {
                map[i] = br.readLine();
            }
            int candyCnt = 0;
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if(map[i].charAt(j) == 111) {
                        if(isVerticalCandy(i, j) || isHorizontalCandy(i, j))
                            candyCnt++;
                    }
                }
            }
            ans.append(candyCnt).append("\n");
            br.readLine();
        }
        System.out.println(ans);
    }

    static boolean isVerticalCandy(int r, int c) {
        if(r == 0 || r == R - 1) return false;
        return map[r - 1].charAt(c) == 118 && map[r + 1].charAt(c) == 94;
    }

    static boolean isHorizontalCandy(int r, int c) {
        if(c == 0 || c == C - 1) return false;
        return map[r].charAt(c - 1) == 62 && map[r].charAt(c + 1) == 60;
    }







}

