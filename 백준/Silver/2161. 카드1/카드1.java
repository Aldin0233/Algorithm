import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new LinkedList<>();
        N = Integer.parseInt(br.readLine());
        for(int i = 1 ; i <= N ; i++) {
            q.add(i);
        }
        while(q.size() > 1) {
            int x = q.remove();
            ans.append(x).append(' ');
            q.offer(q.poll());
        }
        ans.append(q.poll());
        System.out.println(ans);
    }







}