package datastructure.monostack;

import java.util.*;

/**
 * 小Q在周末的时候和他的小伙伴来到大城市逛街，一条步行街上有很多高楼，共有n座高楼排成一行，楼高用arr表示。
 * 小Q从第一栋一直走到了最后一栋，小Q从来都没有见到这么多的楼，所以他想知道他在每栋楼的位置处能看到多少栋楼呢？
 * （当前面的楼的高度大于等于后面的楼时，后面的楼将被挡住）
 */
public class HighBuilding{
    public int[] tallBuilding(int[] arr) {
        int len = arr.length;
        int[] res = new int[len];

        Arrays.fill(res, 1);

        // look from right to left
        countBuildings(arr, res, 0, len, 1);
        // look to right
        countBuildings(arr, res, len -1, -1, -1);

        return res;
    }

    void countBuildings(int[] arr, int[] res, int start, int end, int delta) {
        Stack<Integer> stack = new Stack<>();

        for(int i = start; i != end; i += delta) {
            res[i] += stack.size();
            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            stack.push(i);
        }
    }
}