package datastructure.segmenttree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class JohnBusiness {

    public int[] business_miniHeap(int[] A, int k) {
        // handle corner cases
        if (A == null || A.length == 0) {
            return new int[0];
        }

        int n = A.length;
        int[] profit = new int[n];
        Queue<Integer> minHeap = new PriorityQueue<>();

        // init the first 0..k-1 prices into the minHeap
        for (int i = 0; i < k; i++) {
            minHeap.add(A[i]);
        }

        // core logic - keep sliding the window, 
        // update the minHeap by removing the left most outdated, and adding the right most new addition. 
        // then always take the current price, the A[i], and subtract that to the minHeap.peek(), 

        // TC O(nk) becasue the remove option tc is O(k)
        for (int i = 0; i < n; i++) {
            if (i - k - 1 >= 0) {
                minHeap.remove(A[i - k - 1]);
            }
            if (i + k < n) {
                minHeap.add(A[i + k]);
            }
            profit[i] = A[i] - minHeap.peek();
            // System.out.println("minHeap = " + minHeap);
        }

        return profit;
    }


    public int[] business_MonoQueue(int[] A, int k) {
        if(A == null || A.length == 0) {
            return new int[0];
        }        
        int n = A.length;
        int res[] = new int[n];

        Deque<Integer> queue = new LinkedList<>();

        for(int i = 0; i < k && i < n; i++) {
            addAndKeepASC(queue, A, i);
        }

        for(int i = 0; i < n; i++) {
            if(i - k - 1 >= 0) {
                if(queue.peekFirst() == i - k - 1) {
                    queue.pollFirst();
                }
            }

            if(i+k < n) {
                addAndKeepASC(queue, A, i + k);
            }

            res[i] = A[i] - A[queue.peekFirst()];
        }

        return res;

    }

    private void addAndKeepASC(Deque<Integer> queue, int[] A, int index){
        while(!queue.isEmpty() && A[queue.peek()] >= A[index]) {
            queue.pollLast();
        }
        queue.offer(index);
    }

    /**
     * @param A: The prices [i]
     * @param k: 
     * @return: The ans array
     */
    public int[] business(int[] A, int k) {
        if(A == null || A.length == 0) {
            return new int[0];
        }

        // TC O(n)
        ST st = new ST(A);
        int[] res = new int[A.length]; 

        // TC O(nlogn)
        for (int i = 0; i < A.length; i++) {
            int val = A[i];
            int start = i - k;
            if(start < 0) 
                start = 0;

            int end = i + k;
            if(end > A.length - 1) 
                end = A.length - 1;

            // int start = i - k < 0 ? 0 : i-k;
            // int end = i + k > A.length - 1 ? A.length-1 : i+k;
            int min = st.query(st.root, start, end);
            res[i] = val-min;
        }

        return res;
    }
}

class STNode{
    int start;
    int end;
    int min;

    STNode left;
    STNode right;

    public STNode(int start, int end, int min){
        this.start = start;
        this.end = end;
        this.min = min;
    }
}

class ST{
    STNode root;

    public ST(int[] nums){
        root = construct(nums, 0, nums.length - 1);
    }

    private STNode construct(int[] nums, int start, int end) {
        if(nums == null || start > end) {
            return null;
        }

        STNode node = new STNode(start, end, Integer.MAX_VALUE);
        if(start == end) {
            node.min = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            STNode left = construct(nums, start, mid);
            STNode right = construct(nums, mid+1, end);

            node.left = left;
            node.right = right;
            node.min = Math.min(left.min, right.min);
        }

        return node;
    }

    public int query(STNode node, int start, int end){

        if(node.start == start && node.end == end) {
            return node.min;
        }

        if(node.left.end >= end) {
            return query(node.left, start, end);
        }

        if(node.right.start <= start) {
            return query(node.right, start, end);
        }

        int minL = query(node.left, start, node.left.end);
        int minR = query(node.right, node.right.start, end);

        return Math.min(minL, minR);
    }
}