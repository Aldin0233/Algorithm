import java.io.*;
import java.util.*;

class Main {

    static int N, B, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] students = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        long cnt = 0;
        for(int i = 0; i < N; i++) {
            students[i] -= B;
            cnt++;
            if(students[i] > 0) {
                int tmp = students[i] / C;
                cnt += students[i] % C == 0 ? tmp : tmp + 1;
            }
        }
        System.out.println(cnt);
    }

}