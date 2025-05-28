import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] scores;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        scores = new int[N];
        for(int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(scores);
        ans = 0;
        int sum = 0;
        int deleteAvgPeople = (int) Math.round(N * 0.15);
        for(int i = deleteAvgPeople; i < N - deleteAvgPeople; i++) {
            sum += scores[i];
        }
        ans = (int) Math.round((double)sum / (N - (deleteAvgPeople * 2)));

        System.out.println(ans);
    }



}
