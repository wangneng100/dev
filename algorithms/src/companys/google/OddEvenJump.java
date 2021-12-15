package companys.google;

import java.util.Map;
import java.util.TreeMap;

public class OddEvenJump {
	
	// brute force方法 时间复杂度O(n^3) 遇到大数据集会超时
	// class Solution {
//	     public int oddEvenJumps(int[] A) {
//	         int n = A.length;
//	         if(n < 1) return 0;
//	         int res = 0;
	        
//	         for(int i = 0;i < n;i++){ //遍历每个位置，分别以它们为起点，如果是good，就res++;
//	             // System.out.println("i = "+i);
//	             if(i == n - 1){
//	                 res++;
//	                 break;
//	             }
	            
//	             int value = A[i];
//	             int index = i; //index是临时记录每次跳到的index
//	             while(index < n){
//	                 //先odd jump
//	                 value = A[index];
//	                 index = findGreater(A, value, index + 1, n);
//	                 if(index == -1) break;
//	                 else if(index == n - 1){
//	                     res++;
//	                     break;
//	                 }
//	                 //再even jump
//	                 value = A[index];
//	                 index = findSmaller(A, value, index + 1, n);
//	                 if(index == -1) break;
//	                 else if(index == n - 1){
//	                     res++;
//	                     break;
//	                 }
//	             }
//	         }
	        
//	         return res;
//	     }
	    
//	     public int findGreater(int[] A, int value, int from, int to){
//	         int min = Integer.MAX_VALUE;
//	         int index = -1;
//	         for(int i = from;i < to;i++){
//	             if(A[i] == value) return i;
//	             if(A[i] > value && A[i] < min){
//	                 min = A[i];
//	                 index = i;
//	             }
//	         }
//	         return index;
//	     }
	    
//	     public int findSmaller(int[] A, int value, int from, int to){
//	         int max = Integer.MIN_VALUE;
//	         int index = -1;
//	         for(int i = from;i < to;i++){
//	             if(A[i] == value) return i;
//	             if(A[i] < value && A[i] > max){
//	                 max = A[i];
//	                 index = i;
//	             }
//	         }
//	         return index;
//	     }
	// }

	//以上方法有需要优化的地方
	//1.首先每次我们都要调用findGreater或者findSmaller,其中有些是重复的，但是我们没有把结果保存下来 ，所以重复地去调用其实计算过的地方。因此我们完全可以从右往左开始遍历，这样我们就能存下一路走来的计算结果，方便地查找greater or equal.有个很好的数据结构是TreeSet,直接用floor表示oddjump中找右边的greaterOrEqual, 用ceiling来找evenjump中找右边的SmallerOrEqual.而这种寻找是logN的时间复杂度
	//2.我们没有很好地处理奇偶跳跃，只是简单地把code block重复地写了两遍

	//方法二：TreeMap
	//时间复杂度 O(nlogn)
    public int oddEvenJumps1(int[] A) {
        int n = A.length;
        TreeMap<Integer, Integer> map = new TreeMap<Integer,Integer>();//<value, index>
        map.put(A[n - 1], n - 1);
        boolean[] odd = new boolean[n]; //odd[i]表示从i处开始oddjump能否最后成功
        boolean[] even = new boolean[n]; //even[i]表示从i处开始evenjump能否最后成功
        odd[n - 1] = true;
        even[n - 1] = true;
        
        for(int i = n - 2;i >= 0;i--){ //从右往左遍历并且将结果记录到boolean[] odd和even中
            Map.Entry oddEntry = map.ceilingEntry(A[i]), evenEntry = map.floorEntry(A[i]);
            if(oddEntry != null) odd[i] = even[(int) oddEntry.getValue()]; //如果本处是用oddjump跳到下处的，那下处肯定是evenjump开始往后跳的
            if(evenEntry != null) even[i] = odd[(int) evenEntry.getValue()];//如果本处是用evenjump跳到下处的，那下处肯定是oddjump开始往后跳的
            map.put(A[i], i);
        }
        
        int res = 0;
        for(int i = 0;i < n;i++){
            if(odd[i]) res++;
        }
        
        return res;
    }
	
	public int oddEvenJumps(int[] A) {
        int result = 1;
        int n = A.length;
        boolean[] higher = new boolean[n];
        boolean[] lower = new boolean[n];
        
        higher[n-1] = true;
        lower[n-1] = true;
        
        TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer>();
        tree.put(A[n-1],n-1);
        for(int i=n-2; i>=0; i--){
            Map.Entry higher_pair = tree.ceilingEntry(A[i]);
            Map.Entry lower_pair = tree.floorEntry(A[i]);
            
            if(higher_pair != null) {
                higher[i] = lower[(int)higher_pair.getValue()];
            }
            
            if(lower_pair != null) {
                lower[i] = higher[(int)lower_pair.getValue()];
            }
            
            tree.put(A[i], i);
            
            if(higher[i]){
                result++;
            }
        }
        return result;
    }
	
	
	public static void main(String[] args) {
		OddEvenJump test = new OddEvenJump();
		
		int[] testArr = new int[] {10,13,12,14,15};
		
		System.out.println(test.oddEvenJumps(testArr));
	}

}
