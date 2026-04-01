#include <stdio.h>
#include <stdlib.h>

static inline int min(int a, int b) {
    return a < b ? a : b;
}

//단계 1부터 스타트
static inline int move(int L, int R, int S) {
    int lToS = S - L;
    int sToR = R - S;
    int minDist = min(lToS, sToR);
    int result = minDist * 2 + 1;
    //왼쪽 이동 X
    if(lToS >= sToR) {
        result--;
    }
    return result;
}

int main() {
    int T;
    int L, R, S;
    scanf("%d", &T);
    for(int t = 1; t <= T; t++) {
        scanf("%d %d %d", &L, &R, &S);
        printf("%d\n", move(L, R, S));
    }
    return 0;
}