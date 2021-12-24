package twopointers;

public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        if(s == null || s.length() < 2) {
            return true;
        }

        int[] pair =  findDifferent(s, 0, s.length() - 1);
        if(pair[0] >= pair[1]) return true;

        int[] leftPair = findDifferent(s, pair[0] + 1, pair[1]);
        int[] rightPair = findDifferent(s, pair[0], pair[1] - 1);

        return leftPair[0] >= leftPair[1] || rightPair[0] >= rightPair[1];
    }

    //return 2 elements array, int[0] = left and int[1]=right when the value are different, or return the same value when left and right meet.
    private int[] findDifferent(String s, int left, int right){
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) {
                break;
            }

            left++;
            right--;
        }

        return new int[]{left, right};  
    }
}
