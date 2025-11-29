import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int ans;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        PriorityQueue<Attraction> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            int stTime = convertTimeToInt(sc.nextInt());
            int edTime = convertTimeToInt(sc.nextInt());
            pq.add(new Attraction(stTime, edTime));
        }
        int lastTime = 600; //10 * 60;
        ans = 0;
        while(!pq.isEmpty()) {
            Attraction curAtt = pq.poll();
            if(curAtt.stTime - 10 > lastTime) {
                ans = Math.max(ans, curAtt.stTime - 10 - lastTime);
            }
            lastTime = Math.max(lastTime, curAtt.edTime + 10);
        }
        ans = Math.max(ans, convertTimeToInt(2200) - lastTime);
        System.out.println(ans);
    }
    
    private static int convertTimeToInt(int time) {
        return (time / 100 * 60) + (time % 100);
    }
    
}

class Attraction implements Comparable<Attraction>{
    int stTime, edTime;
    
    public Attraction(int stTime, int edTime) {
        this.stTime = stTime;
        this.edTime = edTime;
    }
    
    public int compareTo(Attraction other) {
        if(stTime == other.stTime) return other.edTime - stTime;
        return stTime - other.stTime;
    } 
}