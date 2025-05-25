import java.io.*;
import java.util.*;

public class Main {

    static int N, T, P;
    static int[] tShirtOrder;
    static StringBuilder ans = new StringBuilder(); //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        tShirtOrder = new int[6];
        for (int i = 0; i < 6; i++) {
            tShirtOrder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        int tShirtAns = 0;
        for(int i = 0; i < 6; i++) {
            tShirtAns += (tShirtOrder[i] + T - 1) / T;
        }
        ans.append(tShirtAns).append("\n");
        ans.append(N/P).append(" ").append(N%P);
        System.out.println(ans);
    }

}
