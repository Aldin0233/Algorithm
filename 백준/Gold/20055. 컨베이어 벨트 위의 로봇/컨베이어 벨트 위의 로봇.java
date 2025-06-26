
import java.io.*;
import java.util.*;

public class Main {

    static int N, K;

    static int startIdx;

    static Belt[] belt;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new Belt[2 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = new Belt(Integer.parseInt(st.nextToken()));
        }

        startIdx = 0; //시작 위치는 0
        ans = 0;
        do {
            ans++; //가장 처음부터 수행 단계 증가하며 시작
            //1번
            startIdx = (startIdx - 1 + (2 * N)) % (2 * N);
            int endIdx = calIdx(N - 1); //내구도 계산
            if (belt[endIdx].isRobot) belt[endIdx].isRobot = false; //끝이면 즉시 내리기
            //2번
            for (int i = N - 2; i > 0; i--) { //가장 먼저 벨트 위에 올라간 로봇부터
                int curIdx = calIdx(i);
                int nextIdx = calIdx(i + 1);
                if (belt[curIdx].isRobot && !belt[nextIdx].isRobot && belt[nextIdx].duration >= 1) { //로봇부터, 다음 칸에 로봇이 없고 칸의 내구도가 1 이상일때
                    belt[curIdx].isRobot = false; //이동
                    if (nextIdx != endIdx) { //즉시 내리기
                        belt[nextIdx].isRobot = true; //그 외의 경우에 이동
                    }
                    belt[nextIdx].duration--; //이동했으니 내구도는 감소
                }
            }
            //3번
            if (belt[startIdx].duration != 0) { //0이 아니면
                belt[startIdx].isRobot = true; //로봇 올리고
                belt[startIdx].duration--; // 내구도 감소
            }
        } while (checkBeltDurationK()); //4번

        System.out.println(ans);
    }

    private static int calIdx(int idx) { //시작 기준 어느 위치에 있는지 인덱스 계산
        return (startIdx + idx) % (2 * N);
    }

    private static boolean checkBeltDurationK() { //내구도 0인 칸 갯수 체크
        int zeroSize = 0;
        for(int i = 0; i < belt.length; i++) {
            if(belt[i].duration == 0) zeroSize++;
        }
        return zeroSize < K;
    }


}

//로봇 겸 벨트
class Belt {
    int duration;
    boolean isRobot;

    Belt(int duration) {
        this.duration = duration;
        isRobot = false;
    }

}




