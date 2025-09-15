import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String target;
    static int N;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();
        N = Integer.parseInt(br.readLine());
        ans = 0;
        for(int i = 0; i < N; i++) {
            if(hasWord(br.readLine())) ans++;
        }
        System.out.println(ans);
    }

    private static boolean hasWord(String word) {
        return preProcessingWord(word).contains(target);
    }

    private static String preProcessingWord(String word) {
        return word + word.substring(0, target.length() - 1);
    }

}






