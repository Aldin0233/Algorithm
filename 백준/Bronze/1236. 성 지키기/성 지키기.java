import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[] horizontal, vertical;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        horizontal = new boolean[N];
        vertical = new boolean[M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if(str.charAt(j) == 'X') {
                    horizontal[i] = true;
                    vertical[j] = true;
                }
            }
        }

        int needVerticalGuard = 0, needHorizontalGuard = 0;
        for(int i = 0; i < N; i++) {
            if(!horizontal[i]) {
                needHorizontalGuard++;
            }
        }
        for(int i = 0; i < M; i++) {
            if(!vertical[i]) {
                needVerticalGuard++;
            }
        }
        ans = Math.max(needHorizontalGuard, needVerticalGuard);
        System.out.println(ans);
    }


}




