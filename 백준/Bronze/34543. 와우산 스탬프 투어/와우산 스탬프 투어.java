import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int W;
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        int ans = N * 10;
        if(N >= 3) ans += 20;
        if(N == 5) ans += 50;
        if(W > 1000) ans -= 15;
        if(ans < 0) ans = 0;
        System.out.println(ans);
    }





}




