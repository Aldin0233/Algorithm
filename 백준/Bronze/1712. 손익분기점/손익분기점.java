import java.io.*;
import java.util.*;

public class Main {

    static int R, C, M;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());
        if(B >= C) {
            System.out.println(-1);
        } else {
            System.out.println(A / (C - B) + 1);
        }
    }

}
