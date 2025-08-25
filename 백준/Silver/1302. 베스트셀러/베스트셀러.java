import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Map<String, Integer> sells = new HashMap<>();
    static int maxSell = 0;
    static String ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            String book = br.readLine();
            sells.compute(book, (k, v) -> v == null ? 1 : v + 1);
        }
        for(String book : sells.keySet()) {
            if(sells.get(book) > maxSell) {
                maxSell = sells.get(book);
                ans = book;
            } else if(sells.get(book) == maxSell) {
                ans = ans.compareTo(book) < 0 ? ans : book;
            }
        }
        System.out.println(ans);
    }


}



