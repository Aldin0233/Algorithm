import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] x;
    static StringBuilder ans = new StringBuilder();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        x = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }
        if(N == 1) {
            System.out.println("A");
            return;
        }

        if(N == 2) {
            if(x[0] == x[1]) {
                System.out.println(x[0]);
            } else {
                System.out.println("A");
            }
            return;
        }

        if(x[0] == x[1]) {
            for(int i = 2; i < N; i++) {
                if(x[i] != x[0]) {
                    System.out.println("B");
                    return;
                }
            }
            System.out.println(x[0]);
            return;
        }

        int front = x[1] - x[0];
        int back = x[2] - x[1];
        // (a * x1 + b) - (a * x0 + b) = a(x1 - x0);
        // a 는 정수 따라서 back / front는 나머지 0이여야 함
        if(back % front != 0) {
            System.out.println("B");
            return;
        }

        int a = back / front;
        int b = x[1] - (x[0] * a);

        for(int i = 1; i < N - 1; i++) {
            if(x[i] * a + b != x[i + 1]) {
                System.out.println("B");
                return;
            }
        }
        System.out.println(x[N - 1] * a + b);

    }






}

