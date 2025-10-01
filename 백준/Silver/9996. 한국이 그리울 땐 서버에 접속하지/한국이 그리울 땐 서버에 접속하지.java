import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static String Pattern, prefix, suffix;
    static final String YES = "DA\n";
    static final String NO = "NE\n";
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Pattern = br.readLine();
        int starIndex = Pattern.indexOf('*');
        prefix = Pattern.substring(0, starIndex);
        suffix = Pattern.substring(starIndex + 1);

        for(int i = 0; i < N; i++) {
            String fileName = br.readLine();
            if(fileName.length() < prefix.length() + suffix.length()) {
                ans.append(NO);
                continue;
            }
            boolean ok = fileName.startsWith(prefix) && fileName.endsWith(suffix);
            ans.append(ok ? YES : NO);
        }
        System.out.println(ans);
    }

}