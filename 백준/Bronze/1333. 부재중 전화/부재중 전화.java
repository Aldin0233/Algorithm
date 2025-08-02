import java.io.*;
import java.util.*;

public class Main {

    static int N, L, D;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        int songEnd = L;
        int totalTime = N * L + ((N - 1) * 5);
        for(int bell = D; ; bell += D) {
            if(bell >= totalTime) {
                ans = bell;
                break;
            }
            while(bell >= songEnd + 5) {
                songEnd += L + 5;
            }
            if(bell >= songEnd && bell < songEnd + 5) {
                ans = bell;
                break;
            }
        }

        System.out.print(ans);
    }

}


