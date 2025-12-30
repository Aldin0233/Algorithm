import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 0) {
                if(pq.isEmpty()) {
                    ans.append("-1\n");
                } else {
                    ans.append(pq.poll()).append("\n");
                }
            } else {
                for(int j = 0; j < a; j++) {
                    pq.offer(Integer.parseInt(st.nextToken()));
                }
            }
        }

        System.out.println(ans);
    }

}



