package lab5Bonus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OnlyOneChoice {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		boolean[] results = new boolean[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			int[] numbers = new int[N];
			for(int j=0; j<numbers.length; j++) {
				numbers[j] = in.nextInt();
			}
			boolean flag = true;
			boolean f = true;
			for(int j=0; j<numbers.length; j++) {
				for(int k=j+1; k<numbers.length; k++) {
					if(numbers[j] > numbers[k]) {
						if(!flag) {
							f = false;
							break;
						}
						flag = false;
						break;
					}
				}
				if(!f) {
					results[i] = false;
					break;
				}
			}
			if(f) {
				results[i] = true;
			}
		}
		for(int i=0; i<T; i++) {
			if(results[i]) {
				System.out.println("Y");
			}
			else {
				System.out.println("N");
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
