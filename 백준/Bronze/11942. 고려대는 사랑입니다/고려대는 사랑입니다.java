public class Main {
    public static void main(String[] args) {
        System.out.println(univ.korea);
    }
}

enum univ {
    seoul("서울대학교"),
    yonsai("연세대학교"),
    korea("고려대학교");
    
    String message;

    private univ(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
