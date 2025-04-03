import java.io.*;
import java.util.*;

class Main {

    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int zeroCnt = 0, oneCnt = 0;
        for(int i = 0; i < N; i++) {
            int opinion = Integer.parseInt(br.readLine());
            if(opinion == 0) {
                zeroCnt++;
            } else {
                oneCnt++;
            }
        }

        System.out.println(zeroCnt > oneCnt ? "Junhee is not cute!" : "Junhee is cute!");
    }

}