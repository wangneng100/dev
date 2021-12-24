package dfs.combination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        if(candidates == null) {
            return res;
        }

        Set<Integer> set = new HashSet<>();
        for(int in: candidates){
            set.add(in);
        }

        dfs(set, target, 0, new ArrayList<Integer>(), res);
        
        return res;
    }

    private void dfs(Set<Integer> set, int target, int startIndex, List<Integer> combination, List<List<Integer>> res){
        if (target < 0) {
            return;
        }
        else if(target == 0) {
            res.add(new ArrayList<Integer>(combination));
            return;
        }

        for(int i = startIndex; i < set.size(); i++) {
//            combination.add(set.add(null);
//            dfs(set, target - set.get(i), i, combination, res);
//            combination.remove(combination.size()-1);
        }

    }
}
