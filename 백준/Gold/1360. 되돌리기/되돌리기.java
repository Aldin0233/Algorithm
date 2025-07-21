import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Order[] orders;
    static boolean[] isLiveOrder;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        orders = new Order[N];
        isLiveOrder = new boolean[N];
        Arrays.fill(isLiveOrder, true);
        
        //명령부터 받음
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean isType = st.nextToken().equals("type");
            String data = st.nextToken();
            int time = Integer.parseInt(st.nextToken());
            orders[i] = new Order(isType, isType ? (int) data.charAt(0) : Integer.parseInt(data), time);
        }
        
        //뒤에서부터 언도 접근
        int idx = N - 1;
        while(idx >= 0) {
            if(!orders[idx].isType) {
                isLiveOrder[idx] = false;
                int nowTime = orders[idx].time;
                int undoTime = nowTime - orders[idx].getData();
                idx--;
                while(idx >= 0 && orders[idx].time >= undoTime) {
                    isLiveOrder[idx--] = false;
                }
            } else {
                idx--;
            }
        }
        
        //출력
        for(int i = 0; i < N; i++) {
            if(isLiveOrder[i]) {
                ans.append((char) orders[i].getData());
            }
        }

        System.out.println(ans);
    }


}

class Order {
    boolean isType;
    int data;
    int time;

    public Order(boolean isType, int data, int time) {
        this.isType = isType;
        this.data = data;
        this.time = time;
    }

    public int getData() {
        return data;
    }

}


