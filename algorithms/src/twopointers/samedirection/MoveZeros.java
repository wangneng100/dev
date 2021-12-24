package twopointers.samedirection;

public class MoveZeros {
    //use two index, newIdx means result array, oldIdx means old array
    public void moveZeroes(int[] nums) {
        int newIdx = 0, oldIdx = 0;

        while(oldIdx < nums.length) {
            if(nums[oldIdx] != 0) {
                int tmp = nums[oldIdx];
                nums[oldIdx] = nums[newIdx];
                nums[newIdx] = tmp;
                newIdx++;
            }
            oldIdx++;
        }
    }


    public void moveZeroes1(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }

        int[] tmp = new int[nums.length];
        int index = 0;
        for(int num:nums){
            if(num != 0){
                tmp[index++] = num;
            }
        }

        while(index < nums.length){
            tmp[index++]  = 0;
        }

        for(int i = 0; i< nums.length; i++){
            nums[i] = tmp[i];
        }
    }
}
