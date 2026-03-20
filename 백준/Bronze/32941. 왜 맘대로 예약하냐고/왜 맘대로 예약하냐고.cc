#include <stdio.h>

int main() {
    int T, X;
    int N;
    scanf("%d %d %d", &T, &X, &N);
    for(int i = 0; i < N; i++) {
        int K;
        scanf("%d", &K);
        int can = 0;
        for(int j = 0; j < K; j++) {
            int tmp;
            scanf("%d", &tmp);
            can |= (X == tmp);
        }
        if(!can) {
            printf("NO");
            return 0;
        }
    }
    printf("YES");
    return 0;
}