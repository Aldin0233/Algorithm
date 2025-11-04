import java.io.*;
import java.util.*;

public class Main {

    static int E, EM, M, MH, H;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());
        EM = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MH = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        int low = 0;
        int high = 200_000;
        while(low <= high) {
            int mid = (low + high) / 2;
            if(canMake(mid)) low = mid + 1;
            else high = mid - 1;
        }

        System.out.println(high);
    }

    private static boolean canMake(int x) {
        int needE, needH;
        int remainEM = EM, remainMH = MH;
        if(x > E) {
            needE = x - E;
            remainEM -= needE;
            if(remainEM < 0) return false;
        }
        if(x > H) {
            needH = x - H;
            remainMH -= needH;
            if(remainMH < 0) return false;
        }
        int canUseM = M + remainEM + remainMH;
        return canUseM >= x;
    }

}