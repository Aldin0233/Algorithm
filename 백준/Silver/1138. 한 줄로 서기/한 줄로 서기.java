
// Online IDE - Code Editor, Compiler, Interpreter
import java.util.*;
public class Main
{
    static int N;
    static int[] arr;
    static boolean[] visited;
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        visited = new boolean[N];
        findNextPeople: while(true) {
            for(int i = 0; i < N; i++) {
                if(visited[i] || arr[i] > 0) continue;
                visited[i] = true;
                ans.append(i + 1).append(' ');
                for(int j = i - 1; j >= 0; j--) {
                    arr[j]--;
                }
                continue findNextPeople;
            }
            break;
        }
        System.out.println(ans);
    }
}
