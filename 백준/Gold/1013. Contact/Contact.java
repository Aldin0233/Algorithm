
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    static int T;
    static final Pattern needPattern = Pattern.compile("^(100+1+|01)+$");

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String signal = br.readLine();
            if(needPattern.matcher(signal).matches()) {
                ans.append("YES\n");
            } else {
                ans.append("NO\n");
            }
        }

        System.out.println(ans);
    }



}
