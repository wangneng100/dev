package java8;

import java.util.Arrays;

public class Nums {
	
	public static String add(String a, String b) {
        double result = Double.parseDouble(a) + Double.parseDouble(b);

        return String.format("%.2f", result);
        
    }

	
	public static void main(String[] args) {
		int [] array = new int[] {1,3};
		System.out.println(add("7.136","3.009"));
		System.out.println(add("2.344", "0.001"));
		
		System.out.println(2.344 + 0.001);
		System.out.println(7.136 + 3.009);
	}

}
