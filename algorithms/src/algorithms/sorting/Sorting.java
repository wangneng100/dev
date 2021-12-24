package algorithms.sorting;

public class Sorting {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] A) {
        if(A == null || A.length <= 1) {
            return;
        }

        // quickSort(A, 0, A.length - 1);
        int [] tmp = new int[A.length];
        mergeSort(A, 0, A.length - 1, tmp);
    }

    private void quickSort(int[] A, int start, int end) {
        if(start >= end) {
            return;
        }

        int pivot = A[(start + end)/2];

        int left = start;
        int right = end;
        while(left <= right) {
            while(left <= right && A[left] < pivot) {
                left++;
            }

            while(left <= right && A[right] > pivot) {
                right--;
            }

            if(left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                left++;
                right--;
            }
        }

        quickSort(A, start, right);
        quickSort(A, left, end);
    }

    private void mergeSort(int[] A, int start, int end, int[] tmp){
        if(start >= end) {
            return;
        }

        int mid = (start + end)/2;

        mergeSort(A, start, mid, tmp);
        mergeSort(A, mid + 1, end, tmp);

        merge(A, start, end, tmp);

    }

    private void merge(int[] A, int start, int end, int[] tmp) {
        
        int mid = (start + end)/2;
        int left = start;
        int right = mid + 1;
        int tmpIdx = left;

        while(left <= mid && right <= end) {
            if(A[left] < A[right]) {
                tmp[tmpIdx++] = A[left++];
            } else {
                tmp[tmpIdx++] = A[right++];
            }
        }

        while(left <= mid) {
            tmp[tmpIdx++] = A[left++];
        }

        while(right <= end) {
            tmp[tmpIdx++] = A[right++];
        }

        for (int i = start; i <= end; i++) {
            A[i] = tmp[i];
        }
    }
} 
