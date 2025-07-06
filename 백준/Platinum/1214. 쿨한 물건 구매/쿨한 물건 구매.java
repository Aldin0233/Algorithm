import java.io.*;
import java.util.*;

public class Main {

    static int D, P, Q;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        int big, small;
        if(P > Q) {
            big = P;
            small = Q;
        } else if(P < Q) {
            big = Q;
            small = P;
        } else {
            int maxCount = (int) Math.ceil((double)D / P);
            ans = maxCount * P;
            System.out.println(ans);
            return;
        }
        ans = Integer.MAX_VALUE;
        int maxCount = (int) Math.ceil((double)D / big);
        for(int i = 0; i <= maxCount; i++) {
            int bigAmount = big * i;
            int remain = D - bigAmount;
            int smallCount = (remain + small - 1) / small;
            int smallAmount = small * smallCount;
            int totalAmount = bigAmount + smallAmount;
            ans = Math.min(ans, totalAmount);
        }

        System.out.println(ans);
    }



}
