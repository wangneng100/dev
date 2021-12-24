package datastructure.stackandqueue;

import java.util.Stack;

public class HanoiProblem {
	
	public enum Action{
		No, LToM, MToL, MToR, RToM
	}

	public int hanoiProblem2(int num, String left, String mid, String right) {
		Stack<Integer> ls = new Stack<Integer>();
		Stack<Integer> ms = new Stack<Integer>();
		Stack<Integer> rs = new Stack<Integer>();
		ls.push(Integer.MAX_VALUE);
		ms.push(Integer.MAX_VALUE);
		rs.push(Integer.MAX_VALUE);
		
		for(int i = num; i > 0; i--) {
			ls.push(i);
		}
		
		Action[] record = {Action.No};
		
		int step = 0;
		
		while(rs.size() != num +1 ) {
			step += sStackTodStack(record, Action.MToL, Action.LToM, ls, ms, left, mid);
			step += sStackTodStack(record, Action.LToM, Action.MToL, ms, ls, mid, left);
			step += sStackTodStack(record, Action.RToM, Action.MToR, ms, rs, mid, right);
			step += sStackTodStack(record, Action.MToR, Action.RToM, rs, ms, right, mid);
		}
		
		return step;
	}
	
	public static int sStackTodStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> sStack, Stack<Integer> dStack, String from, String to) {
		if(record[0] != preNoAct && sStack.peek() < dStack.peek()) {
			dStack.push(sStack.pop());
			System.out.println("Move " + dStack.peek() + " from " + from + " to "+ to);
			record[0] = nowAct;
			
			return 1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		HanoiProblem hp = new HanoiProblem();
		int i = hp.hanoiProblem2(11, "LEFT", "MID", "RIGHT");
		System.out.println("Total Steps number: " + i);
	}
}
