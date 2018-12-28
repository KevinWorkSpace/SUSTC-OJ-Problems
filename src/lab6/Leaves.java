package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Leaves {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		int[][] results = new int[T][];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			int[] cnts = new int[N+1];
			cnts[1] = 1;
			for(int j=0; j<N-1; j++) {
				int a = in.nextInt();
				int b = in.nextInt();
				cnts[a] += 1;
				cnts[b] += 1;
			}
			results[i] = cnts;
		}
		for(int i=0; i<T; i++) {
			for(int j=0; j<results[i].length; j++) {
				if(results[i][j] == 1) {
					System.out.print(j + " ");
				}
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
