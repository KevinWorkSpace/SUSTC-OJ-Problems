package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TheLargestDistance {
	
	static TreeNode edgeNode;
	static TreeNode[] tns;
	static int maxDistance;
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			tns = new TreeNode[N+1];
			for(int j=0; j<N-1; j++) {
				int a = in.nextInt();
				int b = in.nextInt();
				if(tns[a] == null) {
					tns[a] = new TreeNode(a);
				}
				if(tns[b] == null) {
					tns[b] = new TreeNode(b);
				}
				tns[a].childList.add(tns[b]);
				tns[b].childList.add(tns[a]);
			}
			lastOrder(0, 1);
			results[i] = maxDistance;
			maxDistance = 0;
		}	
		for(int i=0; i<T; i++) {
			System.out.println(results[i]);
		}
	}
	
	public static int lastOrder(int pre, int cur) {
		int first = 0;
		int second = 0;
		for(int i=0; i<tns[cur].childList.size(); i++) {
			if(tns[cur].childList.get(i).val == pre) {
				continue;
			}
			int temp = lastOrder(cur, tns[cur].childList.get(i).val);
	        if (temp > first) {
	            second = first;
	            first = temp;
	        }
	        else if (temp > second) {
	            second = temp;
	        }
		}
		maxDistance = Math.max(maxDistance, first + second);
	    return first + 1; 
	}
	
	static class TreeNode {
		
		private int val;
		
		private ArrayList<TreeNode> childList;
		
		public TreeNode(int val) {
			this.val = val;
			childList = new ArrayList<TreeNode>();
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
