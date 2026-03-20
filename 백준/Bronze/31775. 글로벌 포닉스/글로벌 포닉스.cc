#include <stdio.h>

int main() {
    char S1[20], S2[20], S3[20];
    scanf("%s %s %s", S1, S2, S3);
    int l = (S1[0] == 'l') || (S2[0] == 'l') || (S3[0] == 'l');
    int k = (S1[0] == 'k') || (S2[0] == 'k') || (S3[0] == 'k');
    int p = (S1[0] == 'p') || (S2[0] == 'p') || (S3[0] == 'p');
    if(l && k && p) {
        printf("GLOBAL");
    } else {
        printf("PONIX");
    }
    return 0;
}