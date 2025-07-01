
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;

    static String[] friendInfo;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans = 0;
        friendInfo = new String[N];
        for(int i = 0; i < N; i++) {
            friendInfo[i] = br.readLine();
        }
        for(int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            for(int j = 0; j < N; j++) {
                if(friendInfo[i].charAt(j) == 'Y') {
                    visited[j] = true;
                    for(int k = 0; k < N; k++) {
                        if(friendInfo[j].charAt(k) == 'Y' && !visited[k]) {
                            visited[k] = true;
                        }
                    }
                }
            }
            int cnt = 0;
            for(int j = 0; j < N; j++) {
                if(i != j && visited[j]) {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }

}




