import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] dp;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        dfs(0);
        System.out.println(ans);
    }

    private static void dfs(int tryTime) {
        append(tryTime);
        ans.append("\"재귀함수가 뭔가요?\"\n");
        if(tryTime == N) {
            append(tryTime);
            ans.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
        } else {
            append(tryTime);
            ans.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
            append(tryTime);
            ans.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
            append(tryTime);
            ans.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
            dfs(tryTime + 1);
        }
        append(tryTime);
        ans.append("라고 답변하였지.\n");
    }

    private static void append(int time) {
        for(int i = 0; i < time; i++) {
            ans.append("____");
        }
    }

}
