import java.io.*;
import java.util.*;

public class Main {

    public static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        for(int i = 0 ; i < s.length(); i++) {
            if(s.charAt(i) < 'a') {
                ans.append((char)(s.charAt(i) - 'A' + 'a'));
            } else {
                ans.append((char)(s.charAt(i) - 'a' + 'A'));
            }
        }
        System.out.println(ans);
    }
}
