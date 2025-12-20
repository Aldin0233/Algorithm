import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String expression;
    static StringBuilder changeExpressionResult = new StringBuilder();
    static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("ONE", 1);
        map.put("TWO", 2);
        map.put("THREE", 3);
        map.put("FOUR", 4);
        map.put("FIVE", 5);
        map.put("SIX", 6);
        map.put("SEVEN", 7);
        map.put("EIGHT", 8);
        map.put("NINE", 9);
        map.put("ZERO", 0);
    }
    static final String[] numToStr = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};

    static long calculateResult;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        expression = br.readLine();
        char lastOp = '+';
        int i = 0;
        boolean expectNumber = true;
        while(i < expression.length()) {
            if(expectNumber) {
                int start = i;
                while(i < expression.length() && "+-x/=".indexOf(expression.charAt(i)) == -1) {
                    i++;
                }
                if(start == i) {
                    System.out.println("Madness!");
                    return;
                }
                long num;
                try {
                    num = conversionStringToNum(expression.substring(start, i));
                } catch (Exception e) {
                    System.out.println("Madness!");
                    return;
                }
                changeExpressionResult.append(num);
                calculateResult = apply(calculateResult, num, lastOp);
                expectNumber = false;
            } else {
                char op = expression.charAt(i);
                changeExpressionResult.append(op);
                i++;
                if(op == '=') {
                    break;
                }
                lastOp = op;
                expectNumber = true;
            }
        }
        System.out.println(changeExpressionResult);
        System.out.println(changeToString(calculateResult));
    }

    private static String changeToString(long num) {
        StringBuilder result = new StringBuilder();
        String str = String.valueOf(num);
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '-') {
                result.append("-");
            } else {
                result.append(numToStr[str.charAt(i) - '0']);
            }
        }
        return result.toString();
    }

    private static long apply(long calculateResult, long num, char lastOp) {
        switch(lastOp) {
            case '+': return calculateResult + num;
            case '-': return calculateResult - num;
            case 'x': return calculateResult * num;
            case '/': return calculateResult / num;
        }
        return 0;
    }

    private static long conversionStringToNum(String num) throws Exception {
        int nowIdx = 0;
        StringBuilder result = new StringBuilder();

        while (nowIdx < num.length()) {
            boolean matched = false;
            for(int len = 5; len >= 3; len--) {
                if(nowIdx + len > num.length()) continue;
                String sub = num.substring(nowIdx, nowIdx + len);
                if(map.containsKey(sub)) {
                    result.append(map.get(sub));
                    nowIdx += len;
                    matched = true;
                    break;
                }
            }
            if(!matched) {
                throw new Exception();
            }
        }

        return Long.parseLong(result.toString());
    }
}
