#include <stdio.h>

int main() {
    int N;
    char str[51];
    scanf("%d %s", &N, str);
    int lastPs = 0;
    for(int i = 0; i < N; i++) {
        if(str[i] == 'P') {
            lastPs = 1;
        } else if(lastPs == 1 && str[i] == 'S') {
            lastPs = 2;
        } else if(lastPs == 2 && (str[i] == '4' || str[i] == '5')) {
            continue;
        } else {
            lastPs = 0;
        }
        printf("%c", str[i]);
    }
    
    return 0;
}