package tekion;
/**
 * Given an integer k, return the minimum number of Fibonacci numbers whose sum is equal to k. The same Fibonacci number can be used multiple times.

	The Fibonacci numbers are defined as:
	
	F1 = 1
	F2 = 1
	Fn = Fn-1 + Fn-2 for n > 2.
	It is guaranteed that for the given constraints we can always find such Fibonacci numbers that sum up to k.
 * @author nwang
 *
 */
public class FindMinFibonacciNumbersSet {
	
	//greedy algorithm
	public static int findMinFibonacciNumbersSet(int k) {
		int first = 1;
		int second = 1;
		int third = 0;
		
		while(second <= k) {
			third = first + second;
			first = second;
			second = third;
		}
		
		int res = 0;
		while(k > 0) {
			if(first <= k) {
				k = k - first;
				res++;
				
			}
			third = second;
			second = first;
			first = third - second;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(findMinFibonacciNumbersSet(12));
	}

}
