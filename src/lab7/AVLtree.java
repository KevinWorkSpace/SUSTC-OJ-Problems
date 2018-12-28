package lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class AVLtree {
	
	static int max;
	static boolean res = true;
	static TreeNode node;
	static int maxLength;
	static int[] vis;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		boolean[] results = new boolean[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			TreeNode[] nodes = new TreeNode[n+1];
			vis = new int[n+1];
			boolean[] judgeRoot = new boolean[n+1];
			for(int j=1; j<n+1; j++) {
				nodes[j] = new TreeNode(in.nextInt(), j);
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
				if(isAVL(root, 0)) {
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
	
	public static boolean isAVL(TreeNode root, int len) {  
        if(root == null)  
            return true;
        else {
        	if(root.left != null) {
        		dfs(root.left, len);
        	}
        	int l = maxLength;
        	maxLength = 0;
        	if(root.right != null) {
        		dfs(root.right, len);
        	}
        	int r = maxLength;
        	maxLength = 0;
        	int abs_depth = Math.abs(l-r);  
        	return (abs_depth <= 1) && isAVL(root.left, len+1)&& isAVL(root.right, len+1);        
        }
    } 
	
	public static void dfs(TreeNode root, int len) {
		vis[root.index] = 1;
		if(len > maxLength) {
			maxLength = len;
		}
		if(root.left != null && vis[root.left.index] == 0) {
			dfs(root.left, len+1);
		}
		if(root.right != null && vis[root.right.index] == 0) {
			dfs(root.right, len+1);
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
	
	static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
	
	static class TreeNode {
		int val;
		int index;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int val, int index) {
			this.val = val;
			this.index = index;
		}
	}
}
