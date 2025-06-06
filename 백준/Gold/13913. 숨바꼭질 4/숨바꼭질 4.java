
import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static Queue<Integer> q = new LinkedList<>();
    static int[] visited;
    //답
    static int ansTime = 0;
    static String ansRoute;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        q.add(N);
        int time = 0;
        visited = new int[100001];
        Arrays.fill(visited, -1);
        visited[N] = -2;
        search: while(true) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int now = q.poll();
                if(now == K) {
                    ansTime = time;
                    ansRoute = checkRoute(now);
                    break search;
                }
                if(now + 1 <= 100000 && visited[now + 1] == -1) {
                    visited[now + 1] = now;
                    q.add(now + 1);
                }
                if(now - 1 >= 0 && visited[now - 1] == -1) {
                    visited[now - 1] = now;
                    q.add(now - 1);
                }
                if(now * 2 <= 100000 && visited[now * 2] == -1) {
                    visited[now * 2] = now;
                    q.add(now * 2);
                }
            }
            time++;
        }


        System.out.println(ansTime);
        System.out.println(ansRoute);

    }

    private static String checkRoute(int now) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        while(now != -2) {
            stack.push(now);
            now = visited[now];
        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString();
    }

}

