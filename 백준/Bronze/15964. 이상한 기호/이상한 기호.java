import java.io.*;
import java.util.*;

class Main {

    static long A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A  = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        System.out.println((A * A) - (B * B));
    }

}