import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String state = br.readLine();
        while(!"end".equals(state)) {
            ans.append(checkGameIsValid(state));
            state = br.readLine();
        }
        ans.setLength(ans.length()-1);
        System.out.println(ans);

    }

    private static String checkGameIsValid(String gameState) {
        //승리는 한쪽만 이뤄졌거나 이뤄지지 않았어야 함
        int winResult = checkWin(gameState);
        //X가 하나 더 많거나 수가 같아야 함
        int countResult = checkCount(gameState);
        //승리가 나왔거나 전체가 차있어야 함
        if(winResult == 3 || countResult == -1) return "invalid\n";
        //x 승리일 때는 전체 수가 홀수여야 함 //X가 먼저 둠
        if(winResult == 2 && countResult % 2 == 1) return "valid\n";
        //O 승리일 때는 전체 수가 짝수여야 함 //O가 먼저 둠
        if(winResult == 1 && countResult % 2 == 0) return "valid\n";
        if(winResult == 0 && countResult == 9) return "valid\n";
        return "invalid\n";
    }

    private static int checkCount(String gameState) {
        int xCnt = 0, oCnt = 0;
        for (int i = 0; i < gameState.length(); i++) {
            if (gameState.charAt(i) == 'X') {
                xCnt++;
            } else if(gameState.charAt(i) == 'O'){
                oCnt++;
            }
        }
        if(xCnt == oCnt || xCnt == oCnt + 1) { //X가 한번 더 놨거나, 같아야 함
            return xCnt + oCnt;
        }
        return -1;
    }

    //만약 여러 점을 동시에 놔야 가능한 승리가 있다면 수 판단에서 판별됨
    private static int checkWin(String gameState) {
        //총 8개의 승리 가능 플랜이 있음. 가로 3개, 세로 3개, 대각선 2개
        //만약 대각선 승리가 존재할 시 다른 쪽은 승리 자체가 불가능함 //따라서 체크 불필요하게 유효함
        char c = gameState.charAt(4); //중앙
        if((c == gameState.charAt(0) && c == gameState.charAt(8)) || (c == gameState.charAt(2) && c == gameState.charAt(6))){
            if(c == 'X') return 2;
            else if(c == 'O') return 1;
            else return 0; //한 대각선이라도 .이 차있는 순간 승리는 없다.
        }
        boolean xWin = false, oWin = false;
        for(int i = 0; i < 3; i++) {
            //가로 선 체크
            int row = i * 3;
            if(gameState.charAt(row) == gameState.charAt(row + 1) && gameState.charAt(row) == gameState.charAt(row + 2)){
                if(gameState.charAt(row) == 'X') xWin = true;
                else if(gameState.charAt(row) == 'O') oWin = true;
            }
            //세로 선 체크
            if(gameState.charAt(i) == gameState.charAt(i + 3) && gameState.charAt(i) == gameState.charAt(i + 6)){
                if(gameState.charAt(i) == 'X') xWin = true;
                else if(gameState.charAt(i) == 'O') oWin = true;
            }
        }
        return (xWin ? 2 : 0) + (oWin ? 1 : 0);
    }


}