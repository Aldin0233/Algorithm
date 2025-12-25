import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] needProb;
    static PriorityQueue<Problem> probQ = new PriorityQueue<>();
    static long ans;
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        needProb = new int[5];
        for(int i = 0; i < 5; i++){
            needProb[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            probQ.add(new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int curK = probQ.peek().k;
        int lastT = probQ.peek().t;
        while(!probQ.isEmpty()) {
            Problem p = probQ.poll();
            if(needProb[p.k - 1] <= 0) continue;
            needProb[p.k - 1]--;
            if(p.k == curK) {
                ans += p.t;
                ans += p.t - lastT;
                lastT = p.t;
            } else {
                ans += p.t;
                ans += 60;
                curK = p.k;
                lastT = p.t;
            }
        }
        System.out.println(ans);
    }




}

class Problem implements Comparable<Problem> {
    int k, t;
    Problem(int k, int t) {
        this.k = k;
        this.t = t;
    }

    @Override
    public int compareTo(Problem o) {
        if(this.k == o.k) return this.t - o.t;
        return this.k - o.k;
    }
}

