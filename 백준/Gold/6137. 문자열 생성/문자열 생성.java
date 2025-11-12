import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static String S;
    static StringBuilder T = new StringBuilder();
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = "";
        for (int i = 0; i < N; i++) {
            S += br.readLine().charAt(0);
        }
        int l = 0, r = S.length() - 1;
        int idx = 0;
        while (l <= r) {
            boolean take = false;
            for(int i = 0; l + i <= r; i++) {
                if(S.charAt(l + i) < S.charAt(r - i) ) {
                    T.append(S.charAt(l++));
                    take = true;
                    break;
                } else if(S.charAt(l + i) > S.charAt(r - i)) {
                    T.append(S.charAt(r--));
                    take = true;
                    break;
                }
            }
            //대칭
            if(!take) T.append(S.charAt(l++));
            if(++idx % 80 == 0) T.append('\n');
        }

        System.out.println(T);
    }

}

