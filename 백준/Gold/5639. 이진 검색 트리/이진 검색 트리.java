import java.io.*;
import java.util.*;

public class Main {

    static int[] preOrder = new int[10001];
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = 0;
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            int data = Integer.parseInt(line);
            preOrder[size++] = data;
        }
        postOrder(0, size - 1);
        System.out.println(ans);
    }

    private static void postOrder(int left, int right) {
        if(left > right) return;
        int root = preOrder[left];
        int divide = left + 1;
        while(divide <= right && preOrder[divide] < root) {
            divide++;
        }
        postOrder(left + 1, divide - 1);
        postOrder(divide, right);
        ans.append(root).append("\n");
    }


}
