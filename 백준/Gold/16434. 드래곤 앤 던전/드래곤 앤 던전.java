import java.io.*;
import java.util.*;

public class Main {

    static int N, baseHAtk;
    static Room[] rooms;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        baseHAtk = Integer.parseInt(st.nextToken());
        rooms = new Room[N];
        long  addAtkSum = 0L;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if(type == 1) {
                rooms[i] = new Monster(a, h);
            } else {
                addAtkSum += a;
                rooms[i] = new Potion(a, h);
            }
        }

        State s = new State(baseHAtk + addAtkSum, 1L, 1L);

        for(int i = N - 1; i >= 0; i--) {
            s = rooms[i].backPass(s);
        }

        System.out.println(s.peak);
    }



}

class State {
    final long atk;
    final long req;
    final long peak;
    State(long atk, long req, long peak) {
        this.atk = atk;
        this.req = req;
        this.peak = peak;
    }

}

interface Room {
    State backPass(State s);
}

class Monster implements Room {
    final long a, h;

    Monster(long a, long h) {
        this.a = a;
        this.h = h;
    }

    @Override
    public State backPass(State s) {
        long k = (h + s.atk - 1) / s.atk;
        long damage = a * Math.max(0L, k - 1L); //용사가 선제 타격

        long reqBeforeThisRoom = s.req + damage; //최소 체력 요구량
        long peak = Math.max(s.peak, reqBeforeThisRoom); //최대 체력 갱신
        return new State(s.atk, reqBeforeThisRoom, peak);
    }
}

class Potion implements Room {
    final long a, h;
    Potion(long a, long h) {
        this.a = a;
        this.h = h;
    }

    @Override
    public State backPass(State s) {
        long nextAtk = s.atk - a;
        long nextReq = s.req - h;
        if(nextReq < 1L) nextReq = 1L;
        long peak = Math.max(s.peak, nextReq);
        return new State(nextAtk, nextReq, peak);
    }

}

