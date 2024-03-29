package java8;

import java.util.Arrays;
import java.util.List;

public class TestJDK {
	
	public static void main(String[] args) {
		List<String> strList = Arrays.asList("aaa","abcd","bbb","aaa","abcde","bbba");
		
		strList.stream().filter(x -> x.endsWith("a")).count();
		
		System.out.println(strList);
		System.out.println(Constant.ROAD);
	}

}

interface Constant{
	int WALL = 0;
	int ROAD = 1;
}
