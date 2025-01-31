import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();  
        int M = sc.nextInt();  
        boolean[] brokenButton = new boolean[10];

        for (int i = 0; i < M; i++) {
            brokenButton[sc.nextInt()] = true;
        }

        int minPress = Math.abs(N - 100);  

        for (int i = 0; i <= 999999; i++) {
            String channel = String.valueOf(i);
            
            if (!canUse(channel, brokenButton)) continue;
            
            int pressCount = channel.length() + Math.abs(N - i);
            minPress = Math.min(minPress, pressCount);
        }

        System.out.println(minPress);
    }

    // 채널 번호 입력 가능 여부
    private static boolean canUse(String channel, boolean[] brokenButton) {
        for (char c : channel.toCharArray()) {
            if (brokenButton[c - '0']) {
                return false;
            }
        }
        return true;
    }
}