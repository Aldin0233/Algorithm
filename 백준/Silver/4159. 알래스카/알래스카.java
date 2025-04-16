import java.io.*;
import java.util.*;

public class Main {

    static boolean ans = true;
    static StringBuilder ansStr = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        while(!str.equals("0")) {
            int N = Integer.parseInt(str);

            ans = true;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int now = -199;
            for(int i = 0; i < N; i++) {
                pq.add(Integer.parseInt(br.readLine()));
            }
            int last = 0;
            while(!pq.isEmpty()) {
                last = pq.poll();
                if(now + 200 < last) {
                    ans = false;
                    break;
                }
                now = last;
            }
            if((1422 - last) * 2 > 200) {
                ans = false;
            }
            ansStr.append(ans ? "POSSIBLE\n" : "IMPOSSIBLE\n");
            str = br.readLine();
        }
        System.out.println(ansStr.toString());
    }

}

