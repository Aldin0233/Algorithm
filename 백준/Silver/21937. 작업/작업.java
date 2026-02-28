import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int X;
    static Task[] tasks;
//    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tasks = new Task[N + 1];
        for (int i = 1; i <= N; i++) {
            tasks[i] = new Task(i);
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tasks[b].addBeforeTask(a);
        }

        X = Integer.parseInt(br.readLine());
        int cnt = 0;
        boolean[] visited = new boolean[N + 1];
        Queue<Task> q = new LinkedList<>();
        q.add(tasks[X]);
        while(!q.isEmpty()) {
            Task t = q.poll();
            for(int taskId : t.beforeTasks) {
                if(!visited[taskId]) {
                    visited[taskId] = true;
                    cnt++;
                    q.add(tasks[taskId]);
                }
            }
        }
        System.out.println(cnt);



    }

    static class Task {
        int idx;
//        int beforeTaskProcessed;
//        int willTasks;
        List<Integer> beforeTasks;

        Task(int idx) {
            this.idx = idx;
//            this.beforeTaskProcessed = 0;
//            this.willTasks = 0;
            this.beforeTasks = new ArrayList<>();
        }

        void addBeforeTask(int taskId) {
            beforeTasks.add(taskId);
        }

    }

}

