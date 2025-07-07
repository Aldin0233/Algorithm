import java.io.*;
import java.util.*;

public class Main {

    static int N, L;
    static int[] arr, min;
    static Deque<Integer> dq;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N];
        min = new int[N];
        dq = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(!dq.isEmpty() && dq.peekFirst() <= i - L) {
                dq.pollFirst();
            }

            while(!dq.isEmpty() && arr[dq.peekLast()] >= arr[i]) {
                dq.pollLast();
            }

            dq.addLast(i);

            min[i] = arr[dq.peekFirst()];
        }

        for(int i = 0; i < N; i++) {
            ans.append(min[i] + " ");
        }


        System.out.println(ans);
    }



}
