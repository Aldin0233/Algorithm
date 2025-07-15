import java.io.*;
import java.util.*;

public class Main {

    static int M, F;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        while(M != 0 && F != 0) {
            ans.append(M + F).append("\n");
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            F = Integer.parseInt(st.nextToken());
        }
        System.out.println(ans);
    }

}
