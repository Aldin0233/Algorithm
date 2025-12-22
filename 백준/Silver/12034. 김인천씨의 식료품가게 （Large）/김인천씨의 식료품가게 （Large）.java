import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int N;
    static Map<Integer, Integer> priceCnt;
    static final int MAX_PRICE = 100_000_000;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            ans.append("Case #").append(t).append(": ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            priceCnt = new HashMap<>();
            for (int i = 0; i < 2 * N; i++) {
                int price = Integer.parseInt(st.nextToken());
                priceCnt.put(price, priceCnt.getOrDefault(price, 0) + 1);
                if(priceCnt.get(price) > 0) {
                    ans.append(price).append(" ");
                    int originalPrice = price / 3 * 4;
                    priceCnt.put(originalPrice, priceCnt.getOrDefault(originalPrice, 0) - 1);
                }
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }




}
