package lab7;

import java.util.Scanner;
import java.util.Stack;

public class AVLtree2 {
	
	static int max;
	static boolean res = true;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		boolean[] results = new boolean[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			TreeNode[] nodes = new TreeNode[n+1];
			boolean[] judgeRoot = new boolean[n+1];
			for(int j=1; j<n+1; j++) {
				nodes[j] = new TreeNode(in.nextInt());
			}
			boolean flag = true;
			for(int j=1; j<n+1; j++) {
				int left = in.nextInt();
				int right = in.nextInt();
				nodes[j].left = nodes[left];
				nodes[j].right = nodes[right];
				if(left != 0) {
					if(nodes[left].val >= nodes[j].val) {
						results[i] = false;
						flag = false;
					}
				}
				if(right != 0) {
					if(nodes[right].val <= nodes[j].val) {
						results[i] = false;
						flag = false;
					}
				}
				judgeRoot[left] = true;
				judgeRoot[right] = true;
			}
			if(flag) {
				TreeNode root = null;
				for(int j=1; j<n+1; j++) {
					if(!judgeRoot[j]) {
						root = nodes[j];
						break;
					}
				}
				if(IsBalanced_Solution(root)) {
					inOrder(root);
					results[i] = res;
					res = true;
					max = 0;
				}
				else {
					results[i] = false;
				}
			}
		}
		for(int i=0; i<T; i++) {
			if(results[i]) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
	}
	
	public static void inOrder(TreeNode root){
	    Stack<TreeNode> stack = new Stack<>();
	    TreeNode pNode = root;
	    while(pNode!=null||!stack.isEmpty()){
	        if(pNode!=null){
	            stack.push(pNode);
	            pNode = pNode.left;
	        }
	        else{
	            TreeNode node = stack.pop();
	            if(node.val > max) {
	            	max = node.val;
	            }
	            else {
	            	res = false;
	            	break;
	            }
	            pNode = node.right;
	        }
	    }
	}
	
	 public static boolean IsBalanced_Solution(TreeNode root) {
	        if (root == null) {
	            return true;
	        }
	        if (Math.abs(MaxDepth(root.left) - MaxDepth(root.right)) > 1)
	            return false;
	        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
	 }

    public static int MaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(MaxDepth(root.left), MaxDepth(root.right));
    }
    
    static class TreeNode {
		TreeNode left;
		TreeNode right;
		int val;
		
		TreeNode(int val) {
			this.val = val;
		}
	}

}
