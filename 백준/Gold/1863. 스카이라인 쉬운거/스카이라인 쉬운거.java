import java.io.*;
import java.util.*;

public class Main {

    static int N;

    static Stack<Integer> stack = new Stack<>();

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty() && stack.peek() > y) { //지금보다 높은 건물 종료
                stack.pop();
                ans++;
            }

            if(!stack.isEmpty() && stack.peek() == y) { //이전 건물이 다시 나올 수도 있음
                continue;
            }

            if(y > 0) { //지면이면 제외
                stack.push(y);
            }
        }

        ans += stack.size();

        System.out.print(ans);
    }



}



