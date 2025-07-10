import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static String[] map;
    static int maxLength;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N];
        maxLength = 0;
        ans = 0;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
        }
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int k = i; k < N; k++) {
                    int length = k - i;
                    if(length + j >= M || length < maxLength) continue;
                    if(check(i, j, length)) maxLength = length;
                }
            }
        }
        ans = (maxLength + 1) * (maxLength + 1);
        System.out.println(ans);
    }

    private static boolean check(int i, int j, int l) {
        return map[i].charAt(j) == map[i].charAt(j + l) &&
                map[i].charAt(j) == map[i + l].charAt(j) &&
                map[i].charAt(j) == map[i + l].charAt(j + l);
    }

}


