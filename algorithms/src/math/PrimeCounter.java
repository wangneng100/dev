package math;

public class PrimeCounter {
    public int countPrimes(int n) {
        // isComposite[n]表示n是否是合数
        boolean[] isComposite = new boolean[n];

        int count = 0;
        // 从最小的合数开始，计算所有含有i为因子（i的倍数），并且小于n的合数
        // 时间复杂度应该是O(n)
        for (int i = 2; i < n; ++i) {
            if (!isComposite[i]) {
                ++count;
                // 当前的i 肯定是质数
                for (int times = 1; i * times < n; ++times) {
                    isComposite[i * times] = true;
                }
            }
        }
        return count;
    }
}
