package lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CompleteBinaryTree {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		boolean[] results = new boolean[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			boolean[] judgeRoot = new boolean[n+1];
			TreeNode[] nodes = new TreeNode[n+1];
			for(int j=1; j<n+1; j++) {
				nodes[j] = new TreeNode();
			}
			for(int j=1; j<n+1; j++) {
				int left = in.nextInt();
				int right = in.nextInt();
				nodes[j].left = nodes[left];
				nodes[j].right = nodes[right];
				judgeRoot[left] = true;
				judgeRoot[right] = true;
			}
			TreeNode root = null;
			for(int j=1; j<n+1; j++) {
				if(!judgeRoot[j]) {
					root = nodes[j];
					break;
				}
			}
			int count = 0;
			ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
	        queue.add(root);
	        count++;
	        while(queue.isEmpty()==false){
	            TreeNode node = queue.remove();
	            if(node.left!=null){
	                queue.add(node.left);
	                count++;
	            }
	            else {
	            	if(count != n) {
	            		results[i] = false;
	            		break;
	            	}
	            	else {
	            		results[i] = true;
	            		break;
	            	}
	            }
	            if(node.right!=null){
	                queue.add(node.right);
	                count++;
	            }
	            else {
	            	if(count != n) {
	            		results[i] = false;
	            		break;
	            	}
	            	else {
	            		results[i] = true;
	            		break;
	            	}
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
	}
}
