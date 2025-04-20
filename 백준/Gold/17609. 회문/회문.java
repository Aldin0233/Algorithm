import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            String str = br.readLine();
            ans.append(isPurePalindrome(str, 0, str.length() - 1)).append("\n");
        }
        System.out.println(ans);
    }

    private static int isPurePalindrome(String str, int left, int right) {
        while(left < right) {
            if(str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {
                boolean isLeftDeletePalindrome = isSimiPalindrome(str, left + 1, right);
                boolean isRightDeletePalindrome = isSimiPalindrome(str, left, right - 1);
                return (isLeftDeletePalindrome || isRightDeletePalindrome) ? 1 : 2;
            }
        }
        return 0;
    }

    private static boolean isSimiPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }


}