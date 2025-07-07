import java.io.*;
import java.util.*;

public class Main {

    static int N, L;
    static int[] arr, min; //배열, 최솟값
    static Deque<Integer> dq; //덱을 이용해 슬라이딩 윈도우
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
            //L이상 부터 앞부터 순서대로(슬라이딩 윈도우) 제거
            if(!dq.isEmpty() && dq.peekFirst() <= i - L) {
                dq.pollFirst();
            }
            //뒤에서부터(앞은 주기적으로 제거)
            //현재 투입되는 값보다 큰 값들은 미리 제거함
            while(!dq.isEmpty() && arr[dq.peekLast()] >= arr[i]) {
                dq.pollLast();
            }

            dq.addLast(i); //현재 값 추가

            min[i] = arr[dq.peekFirst()]; // 첫번째 값이 현재 가장 낮은 값
        }

        for(int i = 0; i < N; i++) {
            ans.append(min[i] + " ");
        }


        System.out.println(ans);
    }



}
