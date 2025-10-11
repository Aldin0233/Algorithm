import java.io.*;
import java.util.*;

public class Main {

    static int T, N;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>();
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();
                String Type = st.nextToken();
                map.put(Type, map.getOrDefault(Type, 0) + 1);
            }

            int result = 1;
            for(int count : map.values()) {
                result *= count + 1;
            }
            ans.append(result - 1).append("\n");
        }
        System.out.println(ans);
    }
}
