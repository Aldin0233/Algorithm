#include <stdio.h>
#include <stdlib.h>

//2476 주사위 게임


static inline int max(int a, int b) {
    return a > b ? a : b;
}

int roll(int diceResult[3]) {
    int a = diceResult[0];
    int b = diceResult[1];
    int c = diceResult[2];
    if(a == b && a == c) {
        return 10000 + a * 1000;
    } else if(a == b || a == c) {
        return 1000 + a * 100;
    } else if(b == c) {
        return 1000 + b * 100;
    } else {
        int biggest = max(a, max(b, c));
        return biggest * 100;
    }
}

int main() {
    int N;
    scanf("%d", &N);
    int diceResult[3];
    int maxResult = 0;
    for(int i = 0; i < N; i++) {
        scanf("%d %d %d", &diceResult[0], &diceResult[1], &diceResult[2]);
        maxResult = max(maxResult, roll(diceResult));
    }
    printf("%d", maxResult);
    return 0;
}

