package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Game {
	
	static int maxLength;
	static int[] counts;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		boolean[] results = new boolean[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			counts = new int[N];
			Node[] tns = new Node[N+1];
			for(int j=1; j<N+1; j++) {
				tns[j] = new Node(in.nextInt());
			}
			for(int j=0; j<N-1; j++) {
				int a = in.nextInt();
				int b = in.nextInt();
				tns[a].children.add(tns[b]);
			}
			maxLength = 0;
			if(N == 1) {
				if(tns[1].val == 1) {
					results[i] = true;
				}
			}
			else {
				dfs(tns[1], 0);
				for(int j=0; j<=maxLength; j++) {
					if(counts[j] % 2 == 1) {
						results[i] = true;
						break;
					}
				}
			}
		}
		for(int i=0; i<T; i++) {
			if(results[i]) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
	}
	
	public static void dfs(Node root, int len) {
		if(root.val == 1) {
			counts[len]++;
		}
		if(len > maxLength) {
			maxLength = len;
		}
		for(int i=0; i<root.children.size(); i++) {
			dfs(root.children.get(i), len+1);
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
