import java.io.*;
import java.util.*;

public class Main {

    static String document, word;
    static int ans; //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        document = br.readLine();
        word = br.readLine();
        ans = 0;
        int idx = 0;
        while(true) {
            int foundIdx = document.indexOf(word, idx);
            if(foundIdx == -1) {
                break;
            }
            ans++;
            idx = foundIdx + word.length();
        }

        System.out.println(ans);
    }


}
