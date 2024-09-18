import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt =0;
        String str = sc.nextLine();
        if(str.length()>0&&str.charAt(0)==' ') {
        	str = str.substring(1);
        }
        if(str.isEmpty()) {
        	System.out.println(0);
        } else {
        	String[] arr = str.split(" ");
            System.out.println(arr.length);
        }
    }
    
}