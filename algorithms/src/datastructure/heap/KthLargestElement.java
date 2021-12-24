package datastructure.heap;

import java.util.PriorityQueue;

public class KthLargestElement {
    public int kthLargestElement(int k, int[] nums) {
        //max heap O(nlogn)
        // PriorityQueue<Integer> heap = new PriorityQueue<>((a,b)->b-a);
        // for(int num: nums) {
        //     heap.offer(num);
        // }

        // int res = 0;
        // for(int i = 0; i < k; i++) {
        //     res = heap.poll();
        // }

        // return res;
        //mini heap o(nlogk)
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int num: nums) {
            heap.offer(num);
            if(heap.size() > k) {
                heap.poll();
            }
        }

        return heap.peek();

    }
}
