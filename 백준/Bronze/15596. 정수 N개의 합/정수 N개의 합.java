import java.io.*;
import java.util.*;

public class Test {
    long sum(int[] a) {
        long ans = Arrays.stream(a).asLongStream().sum();
        return ans;
    }
}
