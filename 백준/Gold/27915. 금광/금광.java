import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        depth = new int[N + 1];
        depth[1] = 0;
        int odd = 0, even = 1;
        for(int i = 2; i <= N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            depth[i] = depth[parent] + 1;
            if(depth[i] % 2 == 0) even++;
            else odd++;
        }

        System.out.println(Math.max(even, odd));
    }


}