package lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BubbleSort {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		int[][] results = new int[T][];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			results[i] = new int[n];
			int num = 0;
			int K = in.nextInt();
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			for(int j=0; j<n; j++) {
				if(pq.size() < K) {
					int m = in.nextInt();
					pq.add(m);
				}
				else {
					int q = in.nextInt();
					pq.add(q);
					results[i][num++] = pq.poll();
				}
			}
			while(pq.size() > 0) {
				results[i][num++] = pq.poll();
			}
		}
		for(int i=0; i<T; i++) {
			for(int j=0; j<results[i].length; j++) {
				System.out.print(results[i][j] + " ");
			}
			System.out.println();
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
