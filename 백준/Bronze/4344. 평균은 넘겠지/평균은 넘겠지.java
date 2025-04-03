import java.io.*;
import java.util.*;

public class Main {

    static int R, C, M;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        C = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < C ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] scores = new int[N];
            double sum = 0;
            for(int j = 0 ; j < N ; j++) {
                scores[j] = Integer.parseInt(st.nextToken());
                sum += scores[j];
            }
            double avg = sum / N;
            double cnt = 0;
            for(int j = 0 ; j < N ; j++) {
                if(scores[j] > avg) {
                    cnt++;
                }
            }
            System.out.println(String.format("%.3f%%", cnt/N*100));
        }
    }



}

