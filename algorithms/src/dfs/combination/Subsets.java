package dfs.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null) {
            return res;
        }

        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }

    //递归的定义
    private void dfs(int[] nums, int index, List<Integer> subset, List<List<Integer>> res) {
        // 递归的出口
        if(index == nums.length) {
            res.add(new ArrayList<Integer>(subset));
            return;
        }

        // 递归的拆解
        //pick the number on index 
        subset.add(nums[index]);
        dfs(nums, index + 1, subset, res);

        //not pick the number on index 
        subset.remove(subset.size() - 1); 
        dfs(nums, index + 1, subset, res);
    }

    // 这个方法更通用
    private void dfs2(int[] nums, int startIndex, List<Integer> subset, List<List<Integer>> res) {
        // 递归的出口
        res.add(new ArrayList<Integer>(subset));

        for(int i = startIndex; i < nums.length; i++) {
            subset.add(nums[i]);
            dfs(nums, i+1, subset, res);
            subset.remove(subset.size() - 1); //backtracking
        }
    }

    public static void main(String[] args) {
    	HashSet set = new HashSet();
    	set.add(1);
    	
        Subsets ss = new Subsets();
        System.out.println(ss.subsets(new int[]{1,2,3}));
    }
}
