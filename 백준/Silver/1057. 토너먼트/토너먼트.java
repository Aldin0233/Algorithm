import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, A, B;
    static int ans; //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        ans = 0;
        while(A != B) {
            A = (A + 1) / 2;
            B = (B + 1) / 2;
            ans++;
        }

        System.out.println(ans);
    }

}
