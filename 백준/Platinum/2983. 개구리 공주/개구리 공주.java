
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static String dOrder;
    static Map<Integer, List<Flower>> diagA, diagB;
    // / 방향 \ 방향
    // / 방향의 경우 X가 클수록 오른쪽 상단으로, \ 방향의 경우 Y가 클수록 왼쪽 상단으로 이동
    
    // A 우측 상단, D 좌측 하단, B 우측 하단, C 좌측 상단
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dOrder = br.readLine();
        diagA = new HashMap<>();
        diagB = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        int curX, curY;
        curX = Integer.parseInt(st.nextToken());
        curY = Integer.parseInt(st.nextToken());
        Flower startFlower = new Flower(curX, curY);
        checkDiag(startFlower);
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            checkDiag(new Flower(x, y));
        }
        
        //꽃 정렬
        flowerSort();
        int orderIdx = 0;
        //현 상태
        State s = new State();
        s.cur = startFlower;

        while(orderIdx < dOrder.length()) {
            char nowOrder = dOrder.charAt(orderIdx++);
            switch (nowOrder) {
                case 'A': s.moveOnDiagA(1); break;
                case 'D': s.moveOnDiagA(-1); break;
                case 'C': s.moveOnDiagB(1); break;
                case 'B': s.moveOnDiagB(-1); break;
            }
        }
        ans.append(s.cur);
        System.out.println(ans);
    }

    private static void flowerSort() {
        for(List<Flower> flowers : diagA.values()) {
            flowers.sort(Comparator.comparingInt(f -> f.x));
            for(int i = 0; i < flowers.size(); i++) {
                if(i > 0) flowers.get(i).prevA = flowers.get(i - 1);
                if(i < flowers.size() - 1) flowers.get(i).nextA = flowers.get(i + 1);
            }
        }
        for(List<Flower> flowers : diagB.values()) {
            flowers.sort(Comparator.comparingInt(f -> f.y));
            for(int i = 0; i < flowers.size(); i++) {
                if(i > 0) flowers.get(i).prevB = flowers.get(i - 1);
                if(i < flowers.size() - 1) flowers.get(i).nextB = flowers.get(i + 1);
            }
        }
    }

    private static void checkDiag(Flower flower) {
        int a = flower.x - flower.y;
        int b = flower.x + flower.y;
        diagA.computeIfAbsent(a, k -> new ArrayList<>()).add(flower);
        diagB.computeIfAbsent(b, k -> new ArrayList<>()).add(flower);
    }

}

class State {
    Flower cur;

    public void moveOnDiagA(int delta) {
        Flower next = (delta > 0 ? cur.nextA : cur.prevA);
        if(next == null) return;
        removeCurFromChains();
        cur = next;
    }

    public void moveOnDiagB(int delta) {
        Flower next = (delta > 0 ? cur.nextB : cur.prevB);
        if(next == null) return;
        removeCurFromChains();
        cur = next;
    }

    private void removeCurFromChains() {
        if(cur.prevA != null) cur.prevA.nextA = cur.nextA;
        if(cur.nextA != null) cur.nextA.prevA = cur.prevA;

        if(cur.prevB != null) cur.prevB.nextB = cur.nextB;
        if(cur.nextB != null) cur.nextB.prevB = cur.prevB;
    }
}

class Flower {
    int x, y;
    Flower prevA, nextA;
    Flower prevB, nextB;

    Flower(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return x + " " + y;
    }

}










