import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int r, c, k;
    static int[][] arr;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        arr = new int[3][3];
        //기본값 입력
        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //최대 100초까지
        for(int i = 0; i <= 100; i++) {
            //달성 여부 확인
            if(arr.length > r && arr[0].length > c && arr[r][c] == k) {
                System.out.println(i);
                return;
            }
            //행 갯수가 많으면 R, 아니면 C
            if(arr.length >= arr[0].length) {
                arr = R();
            } else {
                arr = C();
            }
        }

        System.out.println(-1);
    }

    private static int[][] R() {
        int maxSizeC = 0; //최대 열 크기
        int[][] newArr = new int[arr.length][];
        for(int i = 0; i < arr.length; i++) {
            //카운팅용 맵
            Map<Integer, Num> map = new HashMap<>();
            for (int j = 0; j < arr[0].length; j++) {
                int num = arr[i][j];
                if (num == 0) continue;
                map.computeIfAbsent(num, Num::new).addCnt();
            }
            //맵을 배열로 전환
            newArr[i] = mapToArr(map);
            maxSizeC = Math.max(maxSizeC, newArr[i].length);
        }
        int[][] res = new int[arr.length][Math.min(100, maxSizeC)];
        for(int i = 0; i < arr.length; i++) { //배열 복사(빈 값은 0)
            System.arraycopy(newArr[i], 0, res[i], 0, Math.min(res[i].length, newArr[i].length));
        }
        return res;
    }

    private static int[][] C() {
        int maxSizeR = 0;
        int[][] newArr = new int[arr[0].length][];
        for(int i = 0; i < arr[0].length; i++) {
            Map<Integer, Num> map = new HashMap<>();
            for(int j = 0; j < arr.length; j++) {
                int num = arr[j][i];
                if (num == 0) continue;
                map.computeIfAbsent(num, Num::new).addCnt();
            }
            newArr[i] = mapToArr(map);
            maxSizeR = Math.max(maxSizeR, newArr[i].length);
        }
        int[][] res = new int[Math.min(100, maxSizeR)][arr[0].length];
        for(int i = 0; i < arr[0].length; i++) {
            for(int j = 0; j < Math.min(res.length, newArr[i].length); j++) {
                res[j][i] = newArr[i][j];
            }
        }
        return res;
    }

    private static int[] mapToArr(Map<Integer, Num> map) {
        int[] nowLine = new int[map.size() * 2];
        PriorityQueue<Num> pq = new PriorityQueue<>(map.values());
        int idx = 0;
        while (!pq.isEmpty()) {
            nowLine[idx++] = pq.peek().num;
            nowLine[idx++] = pq.poll().cnt;
        }
        return nowLine;
    }

}

class Num implements Comparable<Num> {
    int num;
    int cnt;
    public Num(int num) {
        this.num = num;
        this.cnt = 0;
    }

    public void addCnt() {
        this.cnt++;
    }

    public int compareTo(Num o) {
        if(this.cnt == o.cnt) {
            return this.num - o.num;
        }
        return this.cnt - o.cnt;
    }
}





