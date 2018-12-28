package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HongSet {
	
	static int[] vis;
	static boolean[] hits;
	static int[] counts; 
	static int tempCount;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			Node[] tns = new Node[N+1];
			hits = new boolean[N+1];
			counts = new int[N+1];
			vis = new int[N+1];
			for(int j=1; j<N+1; j++) {
				int number = in.nextInt();
				tns[j] = new Node(number, j);
			}
			for(int j=0; j<N-1; j++) {
				int a = in.nextInt();
				int b = in.nextInt();
				tns[a].children.add(tns[b]);
				tns[b].children.add(tns[a]);
			}
			dfs(tns[1]);
			int max = 0;
			for(int j=1; j<N+1; j++) {
				if(counts[j] > max) {
					max = counts[j];
				}
			}
			results[i] = max;
		}
		for(int i=0; i<T; i++) {
			System.out.println(results[i]);
		}
	}
	
	public static int rootdfs(Node root) {
		for(int i=0; i<root.children.size(); i++) {
			if(root.children.get(i).val > root.val) {
				if(hits[root.children.get(i).index]) {
					tempCount += counts[root.children.get(i).index];
				}
				else {
					tempCount = rootdfs(root.children.get(i));
				}
			}
		}
		return tempCount+1;
	}
	
	public static void dfs(Node root) {
		rootdfs(root);
		counts[root.index] = ++tempCount;
		tempCount = 0;
		hits[root.index] = true;
		vis[root.index] = 1;
		for(int i=0; i<root.children.size(); i++) {
			if(vis[root.children.get(i).index] == 0) {
				dfs(root.children.get(i));
			}
		}
	}
	
	static class Node {
		private int val;
		private int index;
		private ArrayList<Node> children;
		
		public Node(int val, int index) {
			this.val = val;
			this.index = index;
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
