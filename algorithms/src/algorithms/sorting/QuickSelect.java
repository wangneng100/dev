package algorithms.sorting;

public class QuickSelect {
	
	public int kthLargestElement(int k, int[] nums) {
        // write your code here
        if(k<1||nums.length==0){
            return -1;
        }
        return quickSort(0,nums.length-1,k,nums);
    }
    private int quickSort(int start,int end,int k,int[] nums){
        if(start >= end) {
            return nums[start];
        }
        int left=start;
        int right=end;
        int p=nums[(start+end)/2];
        while(left<=right){
            if(nums[left]>=p){
                int temp=nums[left];
                if(nums[right]<=p){
                nums[left++]=nums[right];
                nums[right--]=temp;
                
                }else{right--;}
            }else{
                    left++;
                 }
            
        }
        if(end-right>k){
           return quickSort(right+1,end,k,nums);
        }else if(end-right<k){
           return quickSort(start,right,k-end+right,nums);
        }else{
            quickSort(right+1,end,k,nums);
            return nums[right+1];
        }
        
    
    }
    
    
    public static void main(String[] args) {
    	QuickSelect qs = new QuickSelect();
    	
    	qs.kthLargestElement(1, new int[]{1,2,3});
	}

}
