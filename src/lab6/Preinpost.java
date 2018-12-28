package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Preinpost {
	
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		ArrayList[] preResults = new ArrayList[T];
		ArrayList[] inResults = new ArrayList[T];
		ArrayList[] postResults = new ArrayList[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			TreeNode root = new TreeNode(1);
			TreeNode[] tns = new TreeNode[N+1];
			tns[1] = root;
			for(int j=0; j<N-1; j++) {
				int a = in.nextInt();
				int b = in.nextInt();
				if(tns[a] == null) {
					tns[a] = new TreeNode(a);
				}
				if(tns[b] == null) {
					tns[b] = new TreeNode(b);
				}
				if(tns[a].left == null) {
					tns[a].left = tns[b];
				}
				else {
					tns[a].right = tns[b];
				}
			}
			ArrayList preArray = new ArrayList();
			ArrayList inArray = new ArrayList();
			ArrayList postArray = new ArrayList();
			preOrder(root, preArray);
			inOrder(root, inArray);
			postOrder(root, postArray);
			preResults[i] = preArray;
			inResults[i] = inArray;
			postResults[i] = postArray;
		}
		for(int i=0; i<T; i++) {
			for(int j=0; j<preResults[i].size(); j++) {
				System.out.print(preResults[i].get(j) + " ");
			}
			System.out.println();
			for(int j=0; j<inResults[i].size(); j++) {
				System.out.print(inResults[i].get(j) + " ");
			}
			System.out.println();
			for(int j=0; j<postResults[i].size(); j++) {
				System.out.print(postResults[i].get(j) + " ");
			}
			System.out.println();
		}
	}
	
	public static void preOrder(TreeNode node, ArrayList arr) {
		if(node == null) {
			return;
		}
		arr.add(node.val);
		preOrder(node.left, arr);
		preOrder(node.right, arr);
	}
	
	public static void inOrder(TreeNode node, ArrayList arr) {
		if(node == null) {
			return;
		}
		inOrder(node.left, arr);
		arr.add(node.val);
		inOrder(node.right, arr);
	}
	
	public static void postOrder(TreeNode node, ArrayList arr) {
		if(node == null) {
			return;
		}
		postOrder(node.left, arr);
		postOrder(node.right, arr);
		arr.add(node.val);
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
}
