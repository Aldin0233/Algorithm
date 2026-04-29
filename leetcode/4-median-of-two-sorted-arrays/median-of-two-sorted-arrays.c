double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size) {
    int totalSize = nums1Size + nums2Size;
    int *arr = malloc(sizeof(int) * totalSize);

    int nums1Idx = 0;
    int nums2Idx = 0;
    int arrIdx= 0;
    
    //arr 병합, 이미 정렬되서 들어온다.
    while(nums1Idx < nums1Size && nums2Idx < nums2Size) {
        if(nums1[nums1Idx] <= nums2[nums2Idx]) {
            arr[arrIdx++] = nums1[nums1Idx++];
        } else {
            arr[arrIdx++] = nums2[nums2Idx++];
        }
    }

    while(nums1Idx < nums1Size) {
        arr[arrIdx++] = nums1[nums1Idx++];
    }

    while(nums2Idx < nums2Size) {
        arr[arrIdx++] = nums2[nums2Idx++];
    }

    double result;
    //길이가 홀수인지 짝수인지 구분
    if((totalSize & 1) == 1) {
        result = arr[totalSize >> 1];
    } else {
        int mid = totalSize >> 1;
        result = ((double)arr[mid - 1] + arr[mid]) / 2.0;
    }

    return result;
}