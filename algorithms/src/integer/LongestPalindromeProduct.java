package integer;

public class LongestPalindromeProduct {
	
	public static int largestPalindrome(int n) {
		if(n==1)return 9;

		int high = (int) Math.pow(10, n)-1;
		int low = (int) Math.pow(10, n - 1);



		for(int i = high; i >= low; i--)
		{
			long pal = getPalindrome(i);

			for(long j = high; j * j >= pal; j--)    
			{
				if(pal % j == 0 && (pal / j) <= high){

					return (int)(pal%1337);
				}    

			}

		}
		return -1;


	}

	public static long getPalindrome(int num) {

		long tmp = num;
		long result = 0;
		long count = 1;

		while (tmp > 0) {
			result = result * 10 + tmp % 10;
			tmp = tmp / 10;
			count = count * 10;
		}

		return num * count + result;
	}
	
	
	public static void main(String[] args) {
		System.out.print(largestPalindrome(3));
	}

}
