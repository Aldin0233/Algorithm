import java.io.*;
import java.util.*;

public class Main {

    static String str;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        ans.append(checkLength(str));
        System.out.println(ans);
    }
    public static int checkLength(String str) {
        int len = 10;
        for (int i = 1; i < str.length(); i++) {
            if(str.charAt(i) != str.charAt(i - 1)) {
                len += 10;
            } else {
                len += 5;
            }
        }
        return len;
    }






}