import java.io.*;
import java.util.*;

class Main {

    static int A, B, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0;
        for(int i = 0 ; i < 5; i++) {
            if(A == Integer.parseInt(st.nextToken())) cnt++;
        }
        System.out.println(cnt);
    }

}