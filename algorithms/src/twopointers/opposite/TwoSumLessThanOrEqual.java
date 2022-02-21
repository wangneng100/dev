package twopointers.opposite;
//https://www.lintcode.com/problem/609
public class TwoSumLessThanOrEqual {
    public int twoSum5(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n -1;
        Arrays.sort(nums);
        int res = 0;
        while(left < right) {
            int sum = nums[left] + nums[right];
            if(sum <= target) {
                res += right - left;
                left++;
            } else {
                right--;
            }
        }

        return res;
    }
}
