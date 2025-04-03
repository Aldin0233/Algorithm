import java.io.*;
import java.util.*;

class Main {

    static int A, B, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        if(C >= A * B) {
            System.out.println(0);
        } else {
            System.out.println(A * B - C);
        }
    }

}