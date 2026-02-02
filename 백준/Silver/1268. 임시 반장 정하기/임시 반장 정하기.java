import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] history;
    static List<Integer>[][] info = new ArrayList[5][10];
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(List<Integer>[] row : info) {
            for(int i = 1 ; i < 10; i++) {
                row[i] = new ArrayList<>();
            }
        }
        history = new int[N][5];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                history[i][j] = Integer.parseInt(st.nextToken());
                int classNo = history[i][j];
                info[j][classNo].add(i);
            }
        }
        int max = 0;
        int nowNo = 0;
        for(int i = 0; i < N; i++) {
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j < 5; j++) {
                set.addAll(info[j][history[i][j]]);
            }
            if(set.size() > max) {
                max = set.size();
                nowNo = i;
            }
        }


        System.out.println(nowNo + 1);
    }

}
