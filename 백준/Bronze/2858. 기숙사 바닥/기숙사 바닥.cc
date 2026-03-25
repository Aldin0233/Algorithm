#include <stdio.h>

int main() {
    int R, B;
    scanf("%d %d", &R, &B);
    int upAndLeftLen = (R / 2) + 2;
    int dimensionSum = R + B;

    for(int i = 1; i <= upAndLeftLen / 2; i++) {
        int one = i;
        int other = upAndLeftLen - i;
        if(one * other == dimensionSum) {
            //항상 other가 더 큼
            printf("%d %d", other, i);
            break;
        }
    }
    
    return 0;
}