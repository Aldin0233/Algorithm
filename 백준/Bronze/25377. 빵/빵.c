#include <stdio.h>
#include <stdlib.h>

#define MAX 10000

static inline int min(int a, int b) { return a < b ? a : b; }

int main () {
    int N;
    scanf("%d", &N);
    int ans = MAX;
    int A, B;
    for(int i = 0; i < N; i++) {
        scanf("%d %d", &A, &B);
        if(A <= B) ans = min(ans, B);
    }
    printf("%d", ans == MAX ? -1 : ans);
    return 0;
}

