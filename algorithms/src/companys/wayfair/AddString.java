package companys.wayfair;

import java.util.LinkedList;
import java.util.Queue;

public class AddString {
    public String addStrings(String num1, String num2) {
        int carry = 0;

        String res = "";
        int i1 = num1.length() -1, i2 = num2.length() - 1;
        while(i1 >= 0 && i2 >= 0) {
            if(num1.charAt(i1) == ',') {
                res = ',' + res;
            } else {
                int tmp = (num1.charAt(i1) - '0')  + (num2.charAt(i2) - '0');
                res =  ((tmp + carry) % 10) + res;
                carry = (tmp + carry)/10;
            }
            
            i1--;
            i2--;
        }

        while(i1 >= 0) {
            if(num1.charAt(i1) == ',') {
                res = ',' + res;
            } else {
                res = ((num1.charAt(i1) - '0' + carry) % 10) + res;
                carry = (num1.charAt(i1) - '0' + carry)/10;
            }
            
            i1--;
        }
        while(i2 >= 0) {
            if(num2.charAt(i2) == ',') {
                res = ',' + res;
            }
            else {
                res = ((num2.charAt(i2) - '0' + carry) % 10) + res;
                carry = (num2.charAt(i2) - '0' + carry)/10;
            }
            
            i2--;
        }

        if(carry > 0){
            res = carry + res;
        }

        return res;
    }


    public static void main(String[] args) {
        AddString as = new AddString();

        String res = as.addStrings("123777", "12123");
        System.out.println(res);

        res = as.addStrings("111,123,777", "912,123");
        System.out.println(res);

        
        TreeNode a = new TreeNode();
        a.val = "111,123,777";

        TreeNode b = new TreeNode();
        b.val = "12,123";

        TreeNode root = new TreeNode();
        root.val = "900,000";
        root.left = a;
        root.right = b;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        res = "0";
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res = as.addStrings(res, node.val);
            if(node.left != null) {
                queue.offer(node.left);
            }

            if(node.right != null) {
                queue.offer(node.right);
            }
        }

        System.out.println(res);

    }
}

class TreeNode{
    TreeNode left;
    TreeNode right;
    String val;
}
