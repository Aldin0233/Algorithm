import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static char[][] square;
    static int[][][] rowPrefix;
    static int[][][] colPrefix;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        square = new char[N][N];
        rowPrefix = new int[26][N][N];
        colPrefix = new int[26][N][N];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < N; j++) {
                square[i][j] = row.charAt(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for(int a = 0; a < 26; a++) {
                    if(i > 0) colPrefix[a][i][j] += colPrefix[a][i - 1][j];
                    if(j > 0) rowPrefix[a][i][j] += rowPrefix[a][i][j - 1];
                }
                colPrefix[square[i][j] - 'A'][i][j]++;
                rowPrefix[square[i][j] - 'A'][i][j]++;
            }
        }
        ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                ans += findTriangle(i, j);
            }
        }
        System.out.println(ans);
    }

    private static int findTriangle(int row, int col) {
        return findUpTriangle(row, col) + findDownTriangle(row, col) + findSideTriangle(row, col);
    }

    private static int findUpTriangle(int row, int col) {
        int leftCanCount = 0, rightCanCount = 0;
        char c = square[row][col];
        while(row - leftCanCount - 1 >= 0 && checkRowPrefixChar(row - leftCanCount - 1, col, leftCanCount + 1, true, c)) {
            leftCanCount++;
        }
        while(row - rightCanCount - 1 >= 0 && checkRowPrefixChar(row - rightCanCount - 1, col, rightCanCount + 1, false, c)) {
            rightCanCount++;
        }
        return leftCanCount + rightCanCount + Math.min(leftCanCount, rightCanCount);
    }

    private static int findDownTriangle(int row, int col) {
        int leftCanCount = 0, rightCanCount = 0;
        char c = square[row][col];
        while(row + leftCanCount + 1 < N && checkRowPrefixChar(row + leftCanCount + 1, col, leftCanCount + 1, true, c)) {
            leftCanCount++;
        }
        while(row + rightCanCount + 1 < N && checkRowPrefixChar(row + rightCanCount + 1, col, rightCanCount + 1, false, c)) {
            rightCanCount++;
        }
        return leftCanCount + rightCanCount + Math.min(leftCanCount, rightCanCount);
    }

    private static int findSideTriangle(int row, int col) {
        int leftCount = 0, rightCount = 0;
        char c = square[row][col];
        while(col - leftCount - 1 >= 0 && checkColPrefixChar(row, col - leftCount - 1, leftCount + 1, c)) {
            leftCount++;
        }
        while(col + rightCount + 1 < N && checkColPrefixChar(row, col + rightCount + 1, rightCount + 1, c)) {
            rightCount++;
        }
        return leftCount + rightCount;
    }

    private static boolean checkRowPrefixChar(int row, int col, int depth, boolean isLeft, char c) {
        int rightCol = isLeft ? col : col + depth;
        int leftCol = isLeft ? col - depth : col;
        if(leftCol < 0 || rightCol >= N) return false;

        int[][] curPrefix = rowPrefix[c - 'A'];

        int intervalChar = curPrefix[row][rightCol];
        if(leftCol > 0) intervalChar -= curPrefix[row][leftCol - 1];

        return intervalChar == depth + 1;
    }

    private static boolean checkColPrefixChar(int row, int col, int depth, char c) {
        int downRow = row + depth;
        int upRow = row - depth;
        if(upRow < 0 || downRow >= N) return false;

        int[][] curPrefix = colPrefix[c - 'A'];

        int intervalChar = curPrefix[downRow][col];
        if(upRow > 0) intervalChar -= curPrefix[upRow - 1][col];

        return intervalChar == depth * 2 + 1;
    }

}


