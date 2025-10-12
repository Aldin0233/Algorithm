import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int beginKbs1 = 0, beginKbs2 = 0;
        for(int i = 0; i < N; i++) {
            String channel = br.readLine();
            if(channel.equals("KBS1")) beginKbs1 = i;
            else if(channel.equals("KBS2")) beginKbs2 = i;
        }
        ans.append("1".repeat(beginKbs1));
        ans.append("4".repeat(beginKbs1));

        if(beginKbs2 < beginKbs1) beginKbs2++;
        ans.append("1".repeat(Math.max(0, beginKbs2)));
        ans.append("4".repeat(Math.max(0, beginKbs2 - 1)));

        System.out.println(ans);

    }

}

