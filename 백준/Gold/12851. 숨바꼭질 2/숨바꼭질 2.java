import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static Queue<Integer> q;
    static int ansTime = 0, ansWay = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        q.offer(N);
        boolean[] visited = new boolean[100001];
        boolean find = false;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int x = q.poll();
                visited[x] = true;
                if(x == K) {
                    find = true;
                    ansWay++;
                } else {
                    if(x + 1 <= 100000 && !visited[x + 1]) {
                        q.offer(x + 1);
                    }
                    if(x - 1 >= 0 && !visited[x - 1]) {
                        q.offer(x - 1);
                    }
                    if(x * 2 <= 100000 && !visited[x * 2]) {
                        q.offer(x * 2);
                    }
                }
            }
            if(find) {
                break;
            }
            ansTime++;
        }

        System.out.println(ansTime);
        System.out.println(ansWay);
    }


}


