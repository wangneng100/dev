package binarysearch;

public class FirstPostionOfTarget {
    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        // use left + 1 < right rather than left < right
        //[1,2,3] find 3, mid = 1, left = 1, infinit loop
        while(left + 1 < right) {
            System.out.println("left" + left + " right" + right);
            int mid = left + (right - left) / 2;
            
            //if mid element = target, we still need to check if its left element equal to target
            if(nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if(nums[left] == target) {
            return left;
        }

        if(nums[right] == target) {
            return right;
        }

        return -1;
    }
}
