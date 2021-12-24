package datastructure.stackandqueue;

import java.util.Stack;

/**
 * 仅使用一个栈将已知栈从小到大排列
 * @author George Wang
 *
 */
public class SortStack {
	
	public static void sortStack(Stack<Integer> stack) {
		Stack<Integer> newStack = new Stack<Integer>();
		
		while(!stack.isEmpty()) {
			int cur = stack.pop();
			if(newStack.isEmpty()||cur <= newStack.peek()) {
				newStack.push(cur);
			} else {
				while(!newStack.isEmpty()) {
					stack.push(newStack.pop());
					if(newStack.isEmpty() ||cur <= newStack.peek()) {
						newStack.push(cur);
						break;
					}
				}
			}
		}
		
		stack.addAll(newStack);
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(3);
		stack.push(5);
		stack.push(4);
		stack.push(1);
		stack.push(2);
		
		sortStack(stack);
		
		System.out.println(stack);
	}

}
