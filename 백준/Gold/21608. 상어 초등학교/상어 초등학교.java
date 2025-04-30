
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static StudentLikesInfo[] studentLikeInfos;
    static int[][] classRoom;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] score = {0, 1, 10, 100, 1000};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        studentLikeInfos = new StudentLikesInfo[N * N];
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            studentLikeInfos[i] = new StudentLikesInfo(Integer.parseInt(st.nextToken()));
            for(int j = 0; j < 4; j++) {
                studentLikeInfos[i].friendly.add(Integer.parseInt(st.nextToken()));
            }
        }
        classRoom = new int[N][N];
        visited = new boolean[N][N];
        seatSearch();
        calScore();
        System.out.println(ans);
    }

    private static void seatSearch() {
        for(StudentLikesInfo studentLikes : studentLikeInfos) {
            int x = -1;
            int y = -1;
            int closeEmptySeat = -1;
            int friendlySeat = -1;
            for(int i = 0 ; i < N; i++) {
                for(int j = 0 ; j < N; j++) {
                    if(visited[i][j]) continue;
                    int curFriendlySeat = 0;
                    int curCloseEmptySeat = 0;
                    for(int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(inMap(nx, ny)) {
                            if(!visited[nx][ny]) {
                                curCloseEmptySeat++;
                            } else if(studentLikes.friendlyCheck(classRoom[nx][ny])){
                                curFriendlySeat++;
                            }
                        }
                    }
                    if(curFriendlySeat > friendlySeat) {
                        closeEmptySeat = curCloseEmptySeat;
                        friendlySeat = curFriendlySeat;
                        x = i;
                        y = j;
                    } else if(curFriendlySeat == friendlySeat && curCloseEmptySeat > closeEmptySeat){
                        closeEmptySeat = curCloseEmptySeat;
                        x = i;
                        y = j;
                    }
                }
            }
            classRoom[x][y] = studentLikes.studentNum;
            studentLikes.seatPosX = x;
            studentLikes.seatPosY = y;
            visited[x][y] = true;
        }
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static void calScore() {
        for(StudentLikesInfo studentLikes : studentLikeInfos) {
            int x = studentLikes.seatPosX;
            int y = studentLikes.seatPosY;
            int scoreIdx = 0;
            for(int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(inMap(nx, ny) && studentLikes.friendlyCheck(classRoom[nx][ny])){
                    scoreIdx++;
                }
            }
            ans += score[scoreIdx];
        }
    }

}

class StudentLikesInfo {
    int studentNum;
    Set<Integer> friendly;
    int seatPosX, seatPosY;
    public StudentLikesInfo(int studentNum) {
        this.studentNum = studentNum;
        this.friendly = new HashSet<>();
    }

    public boolean friendlyCheck(int student) {
        return friendly.contains(student);
    }
}



