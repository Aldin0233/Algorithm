import java.io.*;
import java.util.*;

public class Main {

    static int R, C, M;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            ans.append(str.charAt(i));
            if(i % 10 == 9) {
                ans.append('\n');
            }
        }
        System.out.println(ans);
    }

}
