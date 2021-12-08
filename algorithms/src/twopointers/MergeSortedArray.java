package twopointers;

public class MergeSortedArray {
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        int idxA = m - 1;
        int idxB = n - 1;

        for (int i = m + n - 1; i >= 0; i--) {
            if(idxA >= 0 && idxB >= 0) {
                int a = A[idxA];
                int b = B[idxB];
                if(a >= b) {
                    A[i] = a;
                    idxA--;
                } else {
                    A[i] = b;
                    idxB--;
                }
                continue;
            }

            if(idxA >= 0) {
                A[i] =  A[idxA];
                idxA--;
            } else {
                A[i] =  B[idxB];
                idxB--;
            }
        }
    }
}
