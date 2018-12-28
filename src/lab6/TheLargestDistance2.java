package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TheLargestDistance2 {
	
	static Node node;
	static int maxLength;
	static int[] vis;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			Node[] tns = new Node[N];
			tns = new Node[N+1];
			vis = new int[N+1];
			for(int j=0; j<N-1; j++) {
				int a = in.nextInt();
				int b = in.nextInt();
				if(tns[a] == null) {
					tns[a] = new Node(a);
				}
				if(tns[b] == null) {
					tns[b] = new Node(b);
				}
				tns[a].children.add(tns[b]);
				tns[b].children.add(tns[a]);
			}
			dfs(tns[1], 0);
			for(int j=0; j<vis.length; j++) {
				vis[j] = 0;
			}
			dfs(node, 0);
			results[i] = maxLength;
			maxLength = 0;
			for(int j=0; j<vis.length; j++) {
				vis[j] = 0;
			}
		}
		for(int i=0; i<T; i++) {
			System.out.println(results[i]);
		}
	}
	
	public static void dfs(Node root, int len) {
		vis[root.val] = 1;
		if(len > maxLength) {
			maxLength = len;
			node = root;
		}
		for(int i=0; i<root.children.size(); i++) {
			if(vis[root.children.get(i).val] == 0) {
				dfs(root.children.get(i), len+1);
			}
		}
	}
	
	static class Node {
		private int val;
		private ArrayList<Node> children;
		
		public Node(int val) {
			this.val = val;
			children = new ArrayList<Node>();
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
}
