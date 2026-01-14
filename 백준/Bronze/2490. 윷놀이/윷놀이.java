import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static final char[] 윷놀이 = {'D', 'C', 'B', 'A', 'E'};
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = 0;
            for(int j = 0; j < 4; j++) {
                num += Integer.parseInt(st.nextToken());
            }
            ans.append(윷놀이[num]).append("\n");
        }

        System.out.println(ans);
    }

}




