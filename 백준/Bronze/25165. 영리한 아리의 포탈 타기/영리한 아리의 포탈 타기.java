import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int start, D;
    static int mR, mC;

    static final String YES = "YES!";
    static final String NO = "NO...";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        mR = Integer.parseInt(st.nextToken());
        mC = Integer.parseInt(st.nextToken());

        if(mR < N) {
            System.out.println(NO);
            return;
        }
        if(N % 2 != D) {
            System.out.println(YES);
        } else {
            System.out.println(NO);
        }

    }




}




