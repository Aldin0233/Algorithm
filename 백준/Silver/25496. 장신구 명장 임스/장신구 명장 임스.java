import java.util.*;
import java.io.*;

public class Main {

    static int nowP, N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int ans = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nowP = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }
        
        while(!pq.isEmpty() && nowP < 200) {
            ans++;
            nowP += pq.poll();
        }
        System.out.println(ans);

    }

}