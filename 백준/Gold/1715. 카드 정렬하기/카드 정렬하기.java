import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
        while(pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            int sum = a + b;
            ans += sum;
            pq.add(sum);
        }
        System.out.println(ans);
    }


}