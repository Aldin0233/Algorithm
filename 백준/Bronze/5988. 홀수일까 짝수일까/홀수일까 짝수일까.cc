#include <stdio.h>

int main() {
    int N;
    scanf("%d", &N);
    for(int i = 0; i < N; i++) {
        char tmp[61];
        scanf("%s", tmp);
        int len = 0;
        while(tmp[len + 1] != '\0') {
            len++;
        }
        char last = tmp[len];

        printf("%s\n", (last & 1) == 1 ? "odd" : "even");
    }
    
    return 0;
}