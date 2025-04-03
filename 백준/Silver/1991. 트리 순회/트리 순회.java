import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Node head = new Node(0);
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = st.nextToken().charAt(0) - 'A';
            int B = st.nextToken().charAt(0) - 'A';
            int C = st.nextToken().charAt(0) - 'A';
            head.insert(A, B, C);
        }
        StringBuilder ans = new StringBuilder();
        head.preOrder(ans);
        ans.append("\n");
        head.inOrder(ans);
        ans.append("\n");
        head.postOrder(ans);

        System.out.println(ans);
    }

}

class Node {
    int value;
    Node left, right;
    public Node(int value) {
        this.value = value;
    }
    public void insert(int value, int left, int right) {
        if(this.value == value) {
            if(left > 0) {
                this.left = new Node(left);
            }
            if(right > 0) {
                this.right = new Node(right);
            }
        }
        if(this.left != null) this.left.insert(value, left, right);
        if(this.right != null) this.right.insert(value, left, right);
    }

    public void preOrder(StringBuilder sb) {
        sb.append((char) (this.value + 'A'));
        if(this.left != null) this.left.preOrder(sb);
        if(this.right != null) this.right.preOrder(sb);
    }

    public void inOrder(StringBuilder sb) {
        if(this.left != null) this.left.inOrder(sb);
        sb.append((char) (this.value + 'A'));
        if(this.right != null) this.right.inOrder(sb);
    }

    public void postOrder(StringBuilder sb) {
        if(this.left != null) this.left.postOrder(sb);
        if(this.right != null) this.right.postOrder(sb);
        sb.append((char) (this.value + 'A'));
    }
}