package dfs.permutation;

/*
Description
Given two strings, write a method to decide if one is a permutation of the other.

What is permutation？Two strings are equal by changing the order of characters.
*/
public class StringPermutation {

    public boolean Permutation(String A, String B) {
        int [] ccount = new int[128];

        for(char c : A.toCharArray()) {
            ccount[c]++;
        }

        for(char c: B.toCharArray()) {
            ccount[c]--;
        }

        for(int count: ccount){
            if(count != 0) {
                return false;
            }
        }

        return true;
    }
    
}
