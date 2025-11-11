import java.io.*;
import java.util.*;

public class Main {

    static Station[] stations = new Station[3];
    static int Q;
    static int result;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < stations.length; i++) {
            stations[i] = new Station(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            result = Integer.MAX_VALUE;
            Position home = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for (int j = 0; j < stations.length; j++) {
                int t = Integer.parseInt(st.nextToken());
                result = Math.min(result, stations[j].atLeastTime(home, t));
            }
            ans.append(result).append("\n");
        }
        System.out.println(ans);
    }


}

class Position {
    int x, y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDistance(Position p) {
        return Math.abs(x - p.x) + Math.abs(y - p.y);
    }
}

class Station {
    Position position;

    public Station(int x, int y) {
        position = new Position(x, y);
    }

    public int atLeastTime(Position home, int intervalTime) {
        int moveTime = position.getDistance(home);
        int atLeastInterval = (int) Math.ceil(((double)moveTime) / intervalTime);
        return atLeastInterval * intervalTime;
    }
}
