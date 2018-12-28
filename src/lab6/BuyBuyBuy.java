package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BuyBuyBuy {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int M = in.nextInt();
			int N = in.nextInt();
			int size = 0;
			int count = 0;
			int[] Ls = new int[1048576]; 
			int[] Ts = new int[1048576]; 
			PriorityQueue<TreeNode> pq = new PriorityQueue<TreeNode>(N, new MyComparator());
			for(int j=0; j<N; j++) {
				int K = in.nextInt();
				if(Ls[K] != 0) {  
					Ls[K]++;	
					TreeNode newNode = new TreeNode(K, Ts[K], Ls[K]); 
					pq.offer(newNode);
				}
				else {
					if(size < M) { 
						Ls[K] = 1; 
						Ts[K] = j; 
						TreeNode newNode = new TreeNode(K, Ts[K], Ls[K]);
						pq.offer(newNode);
						size++;
					}
					else { 
						TreeNode maxNode = pq.poll(); 
						while(Ls[maxNode.K] != maxNode.L || Ts[maxNode.K] != maxNode.time) { 
							maxNode = pq.poll();	
						}
						count++; 	
						Ls[maxNode.K] = 0; 
						Ls[K] = 1;		
						Ts[K] = j;		
						TreeNode newNode = new TreeNode(K, Ts[K], Ls[K]);
						pq.offer(newNode);
					}
				}
			}
			results[i] = count;
		}
		for(int i=0; i<T; i++) {
			System.out.println(results[i]);
		}
	}
	
	static class TreeNode {
		int K;
		int time;
		int L;

		public TreeNode(int K, int time, int L) {
			this.K = K;
			this.time = time;
			this.L = L;
		}
	}
	
	static class MyComparator implements Comparator<TreeNode> {
		@Override
		public int compare(TreeNode t1, TreeNode t2) {
			if(t1.L > t2.L) {
				return -1;
			}
			else if(t1.L < t2.L) {
				return 1;
			}
			else {
				if(t1.time < t2.time) {
					return -1;
				}
				else if(t1.time > t2.time) {
					return 1;
				}
				else {
					return 0;
				}
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
}
