
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static boolean isUp;
    static int[] arr;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String[] s = new String[S.length()];
        for(int i = 0; i < S.length(); i++) {
            s[i] = S.substring(i, S.length());
        }
        Arrays.sort(s);
        for(String str : s) {
            ans.append(str).append("\n");
        }
        System.out.println(ans);
    }

}









