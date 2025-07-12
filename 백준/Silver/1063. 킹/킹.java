import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Board board;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        board = new Board(st.nextToken(), st.nextToken()); //보드 생성
        N = Integer.parseInt(st.nextToken()); //움직이는 횟수
        for(int i = 0; i < N; i++) {
            board.move(DirectionChanger.getDirectionIdx(br.readLine()));
        }
        ans.append(board.getKing()).append("\n").append(board.getRock());
        System.out.println(ans);
    }



}

class Board {
    int[] king, rock;
    //왼쪽 아래가 A1임으로 r값은 역방향으로 정의
    static final int[] dr = {0, 0, -1, 1, 1, 1, -1, -1};
    static final int[] dc = {1, -1, 0, 0, 1, -1, 1, -1};

    Board(String king, String rock) {
        this.king = strToInt(king);
        this.rock = strToInt(rock);
    }

    public void setKing(String king) {
        this.king = strToInt(king);
    }

    public void setKing(int[] king) {
        this.king = king;
    }

    public void setRock(String rock) {
        this.rock = strToInt(rock);
    }

    public void setRock(int[] rock) {
        this.rock = rock;
    }

    public String getKing() {
        return intToStr(king);
    }

    public String getRock() {
        return intToStr(rock);
    }

    public void move(int d) {
        if(d < 0 || d >= 8) return;
        int nr = king[0] + dr[d];
        int nc = king[1] + dc[d];
        //킹이 밖으로 나가면 턴 넘김
        if(outside(nr, nc)) return;
        if(nr == rock[0] && nc == rock[1]) { //킹이 돌 있는 방향으로 이동할 때
            int rockNr = rock[0] + dr[d];
            int rockNc = rock[1] + dc[d];
            //돌이 밖으로 나가면 턴 넘김
            if(outside(rockNr, rockNc)) return;
            setRock(new int[]{rockNr, rockNc});
        }
        setKing(new int[]{nr, nc});
    }

    private boolean outside(int r, int c) { //맵 안에 있는지 확인
        return r < 0 || r >= 8 || c < 0 || c >= 8;
    }

    private int[] strToInt(String str) { //좌표값을 정수 배열로
        return new int[] {str.charAt(1) - '1', str.charAt(0) - 'A'};
    }

    private String intToStr(int[] rc) { //정수 배열을 좌표값으로
        return ((char) (rc[1] + 'A')) + "" + (rc[0] + 1);
    }

}

class DirectionChanger { //방향 값을 인덱스로 전환
    static Map<String, Integer> map = new HashMap<>();
    static {
        map.put("R", 0);
        map.put("L", 1);
        map.put("B", 2);
        map.put("T", 3);
        map.put("RT", 4);
        map.put("LT", 5);
        map.put("RB", 6);
        map.put("LB", 7);
    }

    static int getDirectionIdx(String direction) {
        if(!map.containsKey(direction)) {
            throw new IllegalArgumentException();
        }
        return map.get(direction);
    }
}


