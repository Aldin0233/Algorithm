import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static StringBuilder ans = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();
        N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++) {
            int a = Integer.parseInt(br.readLine());
            left.offer(a);
            right.offer(left.poll());
            if (left.size() < right.size()) {
                left.offer(right.poll());
            }

            ans.append(left.peek()).append("\n");
        }
        System.out.println(ans);
    }


}