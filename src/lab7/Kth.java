package lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Kth {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int K = in.nextInt();
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			for(int j=0; j<n; j++) {
				if(pq.size() < K) {
					pq.add(in.nextInt());
				}
				else {
					int m = in.nextInt();
					if(m > pq.peek()) {
						pq.poll();
						pq.add(m);
					}
				}
			}
			results[i] = pq.peek();
		}
		for(int i=0; i<T; i++) {
			System.out.println(results[i]);
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
