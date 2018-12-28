package lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AdjacencyMatrix {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		int[][][] results = new int[T][][];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] a = new int[n][n];
			for(int j=0; j<m; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				a[x-1][y-1] = 1;
			}
			results[i] = a;
		}
		for(int i=0; i<T; i++) {
			for(int j=0; j<results[i].length; j++) {
				for(int k=0; k<results[i].length; k++) {
					System.out.print(results[i][j][k] + " ");
				}
				System.out.println();
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
