

import java.io.*;
import java.util.*;

public class Main {

    static int W, H, T;
    static int P, Q;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(br.readLine());

        int x = (P + T) % (2 * W);
        int y = (Q + T) % (2 * H);

        if(x > W) x = 2 * W - x;
        if(y > H) y = 2 * H - y;

        System.out.printf("%d %d", x, y);
    }


}


