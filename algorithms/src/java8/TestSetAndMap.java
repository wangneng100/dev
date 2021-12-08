package java8;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class TestSetAndMap {
    class InterviewBit{
        int fun (int n) 
        {
            int result;
            result = fun (n - 1);
            return result;
        }
    } 

    public static void main(String[] args) {
        
        
        TestSetAndMap t = new TestSetAndMap();
        InterviewBit ib = t.new InterviewBit();
        ib.fun(10);
        Set<Integer> set = new HashSet<>();
        Set<Integer> set2 = new TreeSet<>();

        Set<Integer> set3 = new LinkedHashSet<>();
        set3.add(2);
        set3.add(1);
        set3.add(3);

        System.out.println(set3);
    }
    
}
