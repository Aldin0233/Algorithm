import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Problem[] problems = new Problem[N + 1];
        for (int i = 1; i <= N; i++) {
            problems[i] = new Problem(i);
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            problems[a].afters.add(problems[b]);
            problems[b].befores.add(problems[a]);
            problems[b].isFirst = false;
        }
        PriorityQueue<Problem> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if(problems[i].isFirst) {
                pq.add(problems[i]);
            }
        }
        while(!pq.isEmpty()) {
            Problem p = pq.poll();
            ans.append(p.level).append(" ");
            for(Problem problem : p.afters) {
                problem.befores.remove(p);
                if(problem.befores.isEmpty()) pq.add(problem);
            }
        }

        System.out.println(ans);
    }

}

class Problem implements Comparable<Problem> {
    int level;
    Set<Problem> befores;
    List<Problem> afters;
    boolean isFirst;

    public Problem(int level) {
        this.level = level;
        this.befores = new HashSet<>();
        this.afters = new ArrayList<>();
        this.isFirst = true;
    }

    public int compareTo(Problem o) {
        return this.level - o.level;
    }

}


