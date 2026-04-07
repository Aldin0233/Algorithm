#include <stdio.h>
#include <stdlib.h>

static inline int clapGame(int num) {
    int cnt = 0;
    while(num > 0) {
        int tmp = num % 10;
        if(tmp != 0 && tmp % 3 == 0) cnt++;
        num /= 10;
    }
    return cnt;
}

int main () {
    int N;
    scanf("%d", &N);
    int ans = 0;
    for(int i = 1; i <= N; i++) {
        ans += clapGame(i);
    }
    printf("%d", ans);
    return 0;
}

