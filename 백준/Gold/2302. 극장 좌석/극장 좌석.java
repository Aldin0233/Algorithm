
import java.io.*;

public class Main {

    static int N, M;

    static int lastFixedSeat;

    static int[] fibonacci;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        lastFixedSeat = 0;
        //각 고정된 자리 사이에서 나올 수 있는 조합은 피보나치 수열을 따른다.
        makeFibonacci(N);
        ans = 1;
        for(int i = 0; i < M; i++) {
            int newFixedSeat = Integer.parseInt(br.readLine());
            int diff = newFixedSeat - lastFixedSeat - 1;
            lastFixedSeat = newFixedSeat;
            ans *= fibonacci[diff]; //조합 곱셈
        }
        ans *= fibonacci[N - lastFixedSeat];
        System.out.println(ans);
    }

    static void makeFibonacci(int N) {
        fibonacci = new int[N + 1];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i = 2; i <= N; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
    }




}




