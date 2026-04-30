char* convert(char* s, int numRows) {
    int sLen = 0;
    while(s[sLen] != '\0') {
        sLen++;
    }
    char* result = malloc(sizeof(char) * (sLen + 1));
    result[sLen] = '\0';
    if(numRows == 1) {
        for(int i = 0; i < sLen; i++) {
            result[i] = s[i];
        }   
        return result;
    }
    //지그재그 원소 간 거리
    int strDist = (numRows * 2) - 2;
    int resultIdx = 0;
    //지그재그 직선부 채우기
    for(int row = 0; row < numRows; row++) {
        //지그재그 직선부 채우기 용 idx
        int firstIdx = row;
        //지그재그 대각선부 채우기 용 idx
        int secondIdx = (numRows * 2) - 2 - row;       
        //어차피 대각선부가 직선부보다 더 많을 수는 없다.
        while(firstIdx < sLen) {
            if(firstIdx < sLen) {
                result[resultIdx++] = s[firstIdx];
                firstIdx += strDist;
            }
            if(row > 0 && row < numRows - 1 && secondIdx < sLen) {
                result[resultIdx++] = s[secondIdx];
                secondIdx += strDist;
            }
        }
    }
    return result;
}