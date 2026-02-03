import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String L, R;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = st.nextToken();
        R = st.nextToken();
        if(L.length() != R.length()) {
            System.out.println(0);
            return;
        }
        int result = 0;
        for(int i = 0; i < L.length(); i++) {
            if(L.charAt(i) == R.charAt(i)) {
                if(L.charAt(i) == '8') result++;
            } else {
                break;
            }
        }
        System.out.println(result);
    }

}
