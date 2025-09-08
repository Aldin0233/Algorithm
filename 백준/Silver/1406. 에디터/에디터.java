
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static TextEditor te = new TextEditor();
    static Map<Character, Runnable> runNoArgs;
    static {
        runNoArgs = new HashMap<>();
        runNoArgs.put('L', () -> te.moveCursorLeft());
        runNoArgs.put('D', () -> te.moveCursorRight());
        runNoArgs.put('B', () -> te.deleteTextAtCurPos());
    }

    static long ans; //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String oText = br.readLine();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < oText.length(); i++) {
            te.addText(oText.charAt(i));
        }
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            if (runNoArgs.containsKey(split[0].charAt(0))) {
                runNoArgs.get(split[0].charAt(0)).run();
            } else {
                te.addText(split[1].charAt(0));
            }
        }
        System.out.println(te.getText());
    }





}

class TextEditor {
    Node cursor;

    TextEditor() {
        cursor = new Node();
    }


    public void addText(char c) {
        if(cursor.left == null) {
            cursor.left = new Node(c);
            cursor.left.right = cursor;
            return;
        }
        Node newNode = new Node(c);
        cursor.left.right = newNode;
        newNode.left = cursor.left;
        cursor.left = newNode;
        newNode.right = cursor;
    }

    public void moveCursorLeft() {
        if(cursor.left != null) {
            if(cursor.right != null) {
                cursor.right.left = cursor.left;
            }
            cursor.left.right = cursor.right;
            cursor.right = cursor.left;
            cursor.left = cursor.left.left;
        }
    }

    public void moveCursorRight() {
        if(cursor.right != null) {
            if(cursor.left != null) {
                cursor.left.right = cursor.right;
            }
            cursor.right.left = cursor.left;
            cursor.left = cursor.right;
            cursor.right = cursor.right.right;
        }
    }

    public void deleteTextAtCurPos() {
        if(cursor.left != null) {
            cursor.left = cursor.left.left;
            if(cursor.left != null) cursor.left.right = cursor;
        }
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        while(cursor.left != null) moveCursorLeft();
        Node head = cursor.right;
        while(head != null) {
            sb.append(head.data);
            head = head.right;
        }
        return sb.toString();
    }

}

class Node {
    char data;
    Node left, right;
    Node() {}

    Node(char data) {
        this.data = data;
    }

}


