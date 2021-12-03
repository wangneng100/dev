package tekion;

import java.util.PriorityQueue;

public class MedianFinder {
	private PriorityQueue<Integer> loHeap = new PriorityQueue<>((a,b)->(b-a));
    
    private PriorityQueue<Integer> highHeap = new PriorityQueue<>();

    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        int loSize = loHeap.size();
        int highSize = highHeap.size();
        
        if(loSize == 0){
            loHeap.add(num);
            return;
        }
        
        if(loSize == highSize) {
            if(num <= highHeap.peek()) {
                loHeap.add(num);
            } else {
                loHeap.add(highHeap.poll());
                highHeap.add(num);
            }
        } else {
            if(num >= loHeap.peek()) {
                highHeap.add(num);
            } else {
                highHeap.add(loHeap.poll());
                loHeap.add(num);
            }
        }
    }
    
    public double findMedian() {
        int loSize = loHeap.size();
        int highSize = highHeap.size();
        
        if(loSize == highSize) {
            return ((double)(loHeap.peek() + highHeap.peek()))/2;
        } else {
            return loHeap.peek();
        }
    }

}
