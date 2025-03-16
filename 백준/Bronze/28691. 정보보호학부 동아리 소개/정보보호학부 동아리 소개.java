import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        char firstAlphabet = sc.nextLine().charAt(0);
        if(firstAlphabet == 'M') {
            System.out.println("MatKor");
        } else if(firstAlphabet == 'W') {
            System.out.println("WiCys");
        } else if(firstAlphabet == 'C') {
            System.out.println("CyKor");
        } else if(firstAlphabet == 'A') {
            System.out.println("AlKor");
        } else {
            System.out.println("$clear");
        }
    }


}