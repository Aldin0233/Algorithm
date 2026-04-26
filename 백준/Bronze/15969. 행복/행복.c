#include <stdio.h>
#include <stdlib.h>

//15969 행복
/*
한바퀴를 돌기 위해 반복문을 사용해 가장 큰 수와 가장 작은 점수를 찾는다.
*/

static inline int max(int a, int b) {
    return a > b ? a : b;
}

static inline int min(int a, int b) {
    return a < b ? a : b;
}



int main() {
    int N;
    scanf("%d", &N);
    int maxCase = 0, minCase = 1001;
    for(int i = 0; i < N; i++) {
        int num;
        scanf("%d", &num);
        maxCase = max(maxCase, num);
        minCase = min(minCase, num);
    }
    printf("%d", maxCase - minCase);
    return 0;
}

