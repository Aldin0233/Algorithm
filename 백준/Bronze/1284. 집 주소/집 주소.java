import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        while(!target.equals("0")) {
            int result = target.length() + 1;
            for(int i = 0; i < target.length(); i++) {
                char c = target.charAt(i);
                if(c == '1') {
                    result += 2;
                } else if(c == '0') {
                    result += 4;
                } else {
                    result += 3;
                }
            }
            ans.append(result).append("\n");
            target = br.readLine();
        }

        System.out.println(ans);
    }

}



