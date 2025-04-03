import java.io.*;
import java.util.*;

class Main {

    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        int sum = 0;
        int min = -1;
        for (int i = 1; i <= 100; i++) {
            int num = i * i;
            if(num >= M && num <= N) {
                sum += num;
                if(min == -1) {
                    min = num;
                }
            }
        }

        System.out.println(min != -1 ? String.format("%d\n%d", sum, min) : min);
    }

}