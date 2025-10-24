import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static long[] canSongInfos;
    static int maxCoveredSong = 0;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        canSongInfos = new long[N];


        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            String infoStr = st.nextToken();
            long info = 0;
            for(int j = 0; j < M; j++) {
                info += infoStr.charAt(j) == 'Y' ? 1 : 0;
                info <<= 1;
            }
            canSongInfos[i] = info;
        }

        for(int mask = 1; mask < (1 << N); mask++) {
            long combined = 0;
            int cnt = 0;
            for(int i = 0; i < N; i++) {
                if((mask & (1 << i)) != 0) {
                    combined |= canSongInfos[i];
                    cnt++;
                }
            }
            int coveredSong = Long.bitCount(combined);
            if(coveredSong != 0) {
                if(coveredSong > maxCoveredSong) {
                    maxCoveredSong = coveredSong;
                    ans = cnt;
                } else if(coveredSong == maxCoveredSong) {
                    ans = Math.min(ans, cnt);
                }
            }
        }



        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

    }




}