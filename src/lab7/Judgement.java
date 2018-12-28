package lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Judgement {
	
	static int max;
	static boolean res = true;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		boolean results[] = new boolean[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			TreeNode[] nodes = new TreeNode[n+1];
			boolean[] judgeroot = new boolean[n+1];
			for(int j=1; j<n+1; j++) {
				nodes[j] = new TreeNode(in.nextInt());
			}
			boolean flag = true;
			for(int j=0; j<n-1; j++) {
				int father = in.nextInt();
				int child = in.nextInt();
				judgeroot[child] = true;
				if(nodes[child].val > nodes[father].val) {
					if(nodes[father].right != null) {
						results[i] = false;
						flag = false;
					}
					nodes[father].right = nodes[child];
				}
				if(nodes[child].val < nodes[father].val) {
					if(nodes[father].left != null) {
						results[i] = false;
						flag = false;
					}
					nodes[father].left = nodes[child];
				}
			}
			if(flag) {
				TreeNode root = null;
				for(int j=1; j<n+1; j++) {
					if(!judgeroot[j]) {
						root = nodes[j];
						break;
					}
				}
				inOrder(root);
				results[i] = res;
				res = true;
				max = 0;
			}
		}
		for(int i=0; i<T; i++) {
			if(results[i]) {
				System.out.println("Case #" + (i+1) + ": YES");
			}
			else {
				System.out.println("Case #" + (i+1) + ": NO");
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
		TreeNode left;
		TreeNode right;
		int val;
		
		TreeNode(int val) {
			this.val = val;
		}
	}
}
