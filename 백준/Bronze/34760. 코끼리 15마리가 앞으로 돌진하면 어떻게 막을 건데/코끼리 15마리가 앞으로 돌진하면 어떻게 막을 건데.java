import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int max;
    static final int INF = Integer.MAX_VALUE;
//    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        max = 0;
        for(int i = 0; i < 14; i++) {
            int num = Integer.parseInt(st.nextToken());
            max = Math.max(max, num);
        }
        max = Math.max(max + 1, Integer.parseInt(st.nextToken()));
        System.out.println(max);
    }

}

