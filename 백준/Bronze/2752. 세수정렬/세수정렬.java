
import java.io.*;
import java.util.*;

public class Main {

    static int A, B, C;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(Integer.parseInt(st.nextToken()));
        pq.add(Integer.parseInt(st.nextToken()));
        pq.add(Integer.parseInt(st.nextToken()));
        while(!pq.isEmpty()) {
            ans.append(pq.poll()).append(" ");
        }
        System.out.println(ans);
    }



}

