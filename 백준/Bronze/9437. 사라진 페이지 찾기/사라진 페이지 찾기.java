import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, P;
    static int[][] buyCost;
    static int maxAns;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        while(N != 0) {
            P = Integer.parseInt(st.nextToken());
            int half = N / 2;
            int[] candi;
            if(P > half) candi = check(N - P);
            else candi = check(P - 1);
            ans.append(printExceptP(P, candi));
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
        }
        System.out.println(ans);
    }

    static String printExceptP(int P, int[] candi) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            if(candi[i] == P) continue;
            sb.append(candi[i]).append(' ');
        }
        sb.append('\n');
        return sb.toString();
    }

    static int[] check(int page) {
        int frontOne, frontTwo, backOne, backTwo;
        frontOne = page % 2 == 0 ? page + 1 : page;
        frontTwo = frontOne + 1;
        backOne = (N - (page / 2) * 2) - 1;
        backTwo = backOne + 1;
        return new int[]{frontOne, frontTwo, backOne, backTwo};
    }




}

