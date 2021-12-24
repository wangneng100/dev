package dfs.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 给定一个可能具有重复数字的列表，返回其所有可能的子集。

// 子集中的每个元素都是非降序的
// 两个子集间的顺序是无关紧要的
// 解集中不能包含重复子集
public class Subsets2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null) {
            return res;
        }

        Arrays.sort(nums);

        dfs(nums, 0, new ArrayList<Integer>(), res);

        return res;
    }

    private void dfs(int[] nums, int startIndex, List<Integer> subset, List<List<Integer>> res) {
        res.add(new ArrayList<Integer>(subset));

        for (int i = startIndex; i < nums.length; i++) {
            if(i != 0 && nums[i] == nums[i - 1] && i > startIndex) {
                continue;
            }
            subset.add(nums[i]);
            dfs(nums, i + 1, subset, res);
            subset.remove(nums[i]);
        }
    }
}
