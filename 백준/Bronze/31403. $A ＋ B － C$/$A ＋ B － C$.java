import java.io.*;
import java.util.*;

class Main {

    static int A, B, C;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Integer.parseInt(br.readLine());
        B = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());
        ans.append(A + B - C).append("\n");
        String AB = A + "" + B;
        int ABtoInt = Integer.parseInt(AB);
        ans.append(ABtoInt - C);
        System.out.println(ans);
    }

}