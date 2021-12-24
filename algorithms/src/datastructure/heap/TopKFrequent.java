package datastructure.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();

        if(nums == null && nums.length < k) {
            return res;
        }


        Map<Integer, Integer> num2freq = new HashMap<>();
        for(int num:nums) {
            num2freq.put(num, num2freq.getOrDefault(num, 0)+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((a,b)->a.getValue() - b.getValue());
        for(Map.Entry entry: num2freq.entrySet()) {
            heap.offer(entry);
            if(heap.size() > k) {
                heap.poll();
            }
        }

        for(int i = 0; i < k; i++) {
            res.add(heap.poll().getKey());
        }

        return res;
    }
}
