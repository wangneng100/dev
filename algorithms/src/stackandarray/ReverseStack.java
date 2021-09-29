package stackandarray;

import java.util.Stack;

/**
 * 仅使用栈和递归实现反向栈
 * @author George Wang
 *
 */
public class ReverseStack {
	
	public static int getAndRemoveLastElement(Stack<Integer> stack) {

		int result = stack.pop();
		if(stack.isEmpty()) {
			return result;
		} else {
			int last = getAndRemoveLastElement(stack);
			stack.push(result);
			return last;
		}
	}
	
	public static void reverse(Stack<Integer> stack) {
		if(stack.isEmpty()) {
			return;
		}
		int i = getAndRemoveLastElement(stack);
		reverse(stack);
		stack.push(i);
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
//		for(int i = 1; i <= 5; i++)
//        {
//            stack.push(i);
//        }
		System.out.println(stack);
		
		reverse(stack);
		
		System.out.println(stack);
	}

}
