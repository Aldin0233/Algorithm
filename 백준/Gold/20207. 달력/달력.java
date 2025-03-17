import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  

        int[] calendar = new int[366];  
        List<int[]> schedules = new ArrayList<>();

        // 일정 입력 받기
        for (int i = 0; i < N; i++) {
            int S = sc.nextInt();
            int E = sc.nextInt();
            schedules.add(new int[]{S, E});
        }

        schedules.sort((a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        for (int[] schedule : schedules) {
            int S = schedule[0];
            int E = schedule[1];
            for (int i = S; i <= E; i++) {
                calendar[i]++;  
            }
        }

        int totalArea = 0;
        int width = 0;
        int maxHeight = 0;

        for (int day = 1; day <= 365; day++) {
            if (calendar[day] > 0) {  
                width++;
                maxHeight = Math.max(maxHeight, calendar[day]);
            } else if (width > 0) {  
                totalArea += width * maxHeight;
                width = 0;
                maxHeight = 0;
            }
        }

        if (width > 0) {
            totalArea += width * maxHeight;
        }

        System.out.println(totalArea);
    }
}
