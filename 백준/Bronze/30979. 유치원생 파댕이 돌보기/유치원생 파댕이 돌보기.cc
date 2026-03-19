#include <stdio.h>

int main() {
    int T;
    scanf("%d", &T);
    int N;
    scanf("%d", &N);
    int candys[N];

    for(int i = 0; i < N; i++) {
        scanf("%d", &candys[i]);
    }
    int sum = 0;
    
    for(int i = 0; i < N; i++) {
        sum += candys[i];
    }
    int happy = (sum >= T);
    printf("Padaeng_i %s", (happy ? "Happy" : "Cry"));

    return 0;
}