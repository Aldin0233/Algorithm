import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Solution> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Solution(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        ans = 0;
        while (!pq.isEmpty()) {
            Solution sol = pq.poll();
            pq2.add(sol.cupRamen);
            if(pq2.size() > sol.deadline) pq2.poll();
        }
        for(int r: pq2) ans += r;

        System.out.println(ans);
    }

}

class Solution implements Comparable<Solution> {
    int deadline;
    int cupRamen;
    Solution(int deadline, int cupRamen) {
        this.deadline = deadline;
        this.cupRamen = cupRamen;
    }

    @Override
    public int compareTo(Solution o) {
        if(deadline == o.deadline) return o.cupRamen - this.cupRamen;
        return this.deadline - o.deadline;
    }
}

