import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        //이미 있는 문제 처리
        Problem[] originalProblems = new Problem[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int pNo = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            int div = Integer.parseInt(st.nextToken());
            originalProblems[i] = new Problem(pNo, level, div);
        }
        RecommendProblemList.addAll(originalProblems);
        //명령문 처리
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            if(order.equals("add")) {
                RecommendProblemList.add(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));
            } else if(order.equals("solved")) {
                RecommendProblemList.solved(Integer.parseInt(st.nextToken()));
            } else if(order.equals("recommend")) {
                int pNo = RecommendProblemList.recommend(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));
                ans.append(pNo).append("\n");
            } else if(order.equals("recommend2")) {
                int pNo = RecommendProblemList.recommend2(Integer.parseInt(st.nextToken()));
                ans.append(pNo).append("\n");
            } else if(order.equals("recommend3")) {
                int pNo = RecommendProblemList.recommend3(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));
                ans.append(pNo).append("\n");
            }
        }

        System.out.println(ans);
    }

}

class RecommendProblemList {
    static TreeSet<Problem> problemsTs = new TreeSet<>();
    static TreeSet<Problem>[] problemsTsList = new TreeSet[101];
    static Map<Integer, Problem> problemsMap = new HashMap<>();

    static {
        for (int i = 1; i <= 100; i++) {
            problemsTsList[i] = new TreeSet<>();
        }
    }

    static int recommend(int division, int x) {
        if(x == 1) return problemsTsList[division].last().pNo;
        return problemsTsList[division].first().pNo;
    }

    static int recommend2(int x) {
        if(x == 1) return problemsTs.last().pNo;
        return problemsTs.first().pNo;
    }

    static int recommend3(int x, int level) {
        Problem p = null;
        if(x == 1) p = problemsTs.higher(new Problem(0, level, 0));
        else p = problemsTs.lower(new Problem(0, level, 0));
        return p == null ? -1 : p.pNo;
    }

    static void addAll(Problem... problemList) {
        for(Problem problem : problemList) {
            problemsTs.add(problem);
            problemsMap.put(problem.pNo, problem);
            problemsTsList[problem.division].add(problem);
        }
    }

    static Problem add(int pNo, int level, int division) {
        Problem newProblem = new Problem(pNo, level, division);
        problemsTs.add(newProblem);
        problemsTsList[division].add(newProblem);
        problemsMap.put(pNo, newProblem);
        return newProblem;
    }

    static Problem solved(int pNo) {
        Problem p = problemsMap.get(pNo);
        problemsMap.remove(pNo);
        problemsTs.remove(p);
        problemsTsList[p.division].remove(p);
        return p;
    }
}

class Problem implements Comparable<Problem> {
    int pNo;
    int level;
    int division;
    Problem(int pNo, int level, int division) {
        this.pNo = pNo;
        this.level = level;
        this.division = division;
    }

    @Override
    public int compareTo(Problem p) {
        if(this.level == p.level) return this.pNo - p.pNo;
        return this.level - p.level;
    }
}

