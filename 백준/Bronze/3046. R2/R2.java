import java.io.*;
import java.util.*;

public class Main {

    static int A, B, C;
    static int[] cntArr;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        B = (C * 2) - A;
        System.out.println(B);
    }



}


