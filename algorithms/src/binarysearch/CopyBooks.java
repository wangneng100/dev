package binarysearch;

/**
 * binary search on answer
 * total time complexity is O(nlog(pageSum))
 */
public class CopyBooks {
    public int copyBooks(int[] pages, int k) {
        int l = 0;
        int r = pageSum(pages);

        //O(log(pageSum))
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(pages, mid, k)) {
                r = mid;
            } else {
                l = mid;
            }
        }

        if (check(pages, l, k)) {
            return l;
        }
        return r;
    }


    //O(n)
    //limit means the slowest copier spent time
    private boolean check(int[] pages, int limit, int k) {
        int num = 0;
        int left = 0;
        for (int item : pages) {
            if (item > limit) {
                return false;
            }
            if (item > left) {
                num++;
                left = limit;
            }
            left -= item;
        }
        return num <= k;
    }

    //O(n)
    private int pageSum(int[] pages) {
        int sum = 0;
        for (int page : pages) {
            sum += page;
        }
        return sum;
    }
}
