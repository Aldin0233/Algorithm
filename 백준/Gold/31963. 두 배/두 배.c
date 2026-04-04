#include <stdio.h>
#include <stdlib.h>

int main() {
    int N;
    //기존에 학생이 시간 초과가 났던 이유 
    //들어오는 값들이 서로 커지는 소수라면 2배로 커지는 값이 기하급수적으로 늘어남 !
    //long long으로 여유있게 받아주자.
    scanf("%d", &N);
    long long ans = 0;
    long long prev, cur;
    scanf("%lld", &prev);
    for(int i = 1; i < N ; i++) {
        scanf("%lld", &cur);
        while(cur < prev) {
            cur *= 2;
            ans++;
        }
        prev = cur;
    }

    printf("%lld", ans);
    return 0;
}