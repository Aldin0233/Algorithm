
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int N, A, B;
    static boolean[] isPrime = new boolean[100001];
    static Set<Integer> visited;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        checkPrime();
        test: for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            visited = new HashSet<>();
            Queue<Integer> q = new LinkedList<>();
            int fingerSnapCnt = 0;
            q.add(N);
            visited.add(N);
            while(!q.isEmpty()) {
                int size = q.size();
                for(int i = 0; i < size; i++) {
                    int cur = q.poll();
                    if(checkTarget(cur)) {
                        ans.append(fingerSnapCnt).append("\n");
                        continue test;
                    }
                    if(visited.add(cur / 2)) q.offer(cur / 2);
                    if(visited.add(cur / 3)) q.offer(cur / 3);
                    if(cur < B * 3 && visited.add(cur + 1)) q.offer(cur + 1);
                    if(cur > 0 && visited.add(cur - 1)) q.offer(cur - 1);
                }
                fingerSnapCnt++;
            }
            ans.append("-1\n");
        }

        System.out.println(ans);
    }

    private static boolean checkTarget(int cur) {
        return A <= cur && cur <= B && isPrime[cur];
    }

    private static void checkPrime() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for(int i = 2; i <= 100000; i++) {
            if(isPrime[i]) {
                int num = i + i;
                while(num <= 100000) {
                    isPrime[num] = false;
                    num += i;
                }
            }
        }
    }


}










