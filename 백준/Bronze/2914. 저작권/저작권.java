import java.io.*;
import java.util.*;

class Main {

    static int A, I;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(st.nextToken());
        System.out.println(A * (I-1) + 1);
    }

}