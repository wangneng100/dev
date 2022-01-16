package binarysearch;

public class FindKClosestElements {
    public int[] kClosestNumbers_myself(int[] A, int target, int k) {
        int left = 0, right = A.length - 1;

        while(left + 1 < right) {
            int mid = (left + right) / 2;
            if(A[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        int res[] = new int[k];

        for (int i = 0; i < k; i++) {
            if(left < 0) {
                res[i] = A[right++];
                continue;
            }
            if(right > A.length - 1){
                res[i] = A[left--];
                continue;
            }
            if(Math.abs(A[left] - target) <= Math.abs(A[right]- target)) {
                res[i] = A[left--];
            } else {
                res[i] = A[right++];
            }
        }
        return res;

    }


    public int[] kClosestNumbers(int[] A, int target, int k) {
        int left = findLowerClosest(A, target);
        int right = left + 1;
        
        int[] results = new int[k];
        for (int i = 0; i < k; i++) {
            if (isLeftCloser(A, target, left, right)) {
                results[i] = A[left];
                left--;
            } else {
                results[i] = A[right];
                right++;
            }
        }
        
        return results;
    }
    
    private boolean isLeftCloser(int[] A, int target, int left, int right) {
        if (left < 0) {
            return false;
        }
        
        if (right >= A.length) {
            return true;
        }
        
        if (target - A[left] != A[right] - target) {
            return target - A[left] < A[right] - target;
        }
        
        return true;
    }
    
    private int findLowerClosest(int[] A, int target) {
        // find the last element smaller than target
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (A[end] < target) {
            return end;
        }
        if (A[start] < target) {
            return start;
        }
        
        return -1;
    }
}
