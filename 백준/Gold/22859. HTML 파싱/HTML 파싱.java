import java.util.*;

public class Main {

    private static StringBuilder answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String doc = sc.nextLine();
        answer = new StringBuilder();
        parsing(doc);
        System.out.println(answer);
    }

    private static void parsing(String doc) {
        int i = 0;
        i += 6;

        while (i < doc.length() && !(doc.charAt(i) == '<' && doc.startsWith("</main>", i))) {
            i = divTitleParsing(doc, i);
        }
    }

    private static int divTitleParsing(String doc, int start) {
        int i = start;

        while (doc.charAt(i) != '"') {
            i++;
        }
        i++;

        StringBuilder title = new StringBuilder();
        while (doc.charAt(i) != '"') {
            title.append(doc.charAt(i));
            i++;
        }
        answer.append("title : ").append(title).append("\n");
        i+= 2;

        while (i < doc.length()) {
            if (doc.startsWith("</div>", i)) {
                return i + 6; // </div> 건너뛰기
            } else {
                i = pParsing(doc, i + 3); // <p> 길이만큼
            }
        }
        return i;
    }

    private static int pParsing(String doc, int start) {
        int i = start;

        StringBuilder content = new StringBuilder();

        while (i < doc.length()) {
            if (doc.startsWith("</p>", i)) {
                break;
            } else if (doc.charAt(i) == '<') {
                while (i < doc.length() && doc.charAt(i) != '>') {
                    i++;
                }
            } else {
                content.append(doc.charAt(i));
            }
            i++;
        }

        answer.append(content.toString().trim().replaceAll("\\s+", " ")).append("\n");
        return i + 4; //p 닫는 태그 건너뛰기
    }
}