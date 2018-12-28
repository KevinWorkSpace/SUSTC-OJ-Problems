package lab5Bonus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AHardKthSmallestNumber {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			long n = N;
			int[] numbers = new int[N];
			for(int j=0; j<N; j++) {
				numbers[j] = in.nextInt();
			}
			Arrays.sort(numbers);
			int max = numbers[N-1];
			int min = numbers[0];
			int end = max - min;
			int start = 0;
			if(N == 3) {
				int[] ds = new int[3];
				ds[0] = Math.abs(numbers[0] - numbers[1]);
				ds[1] = Math.abs(numbers[0] - numbers[2]);
				ds[2] = Math.abs(numbers[2] - numbers[1]);
				Arrays.sort(ds);
				results[i] = ds[1];
			}
			else if(N == 2) {
				results[i] = Math.abs(numbers[0] - numbers[1]);
			}
			else {
				while(start < end) {
					int mid = (start + end) / 2;
					long count = 0;
					int lp = 0;
					int rp = 1;
					for(lp=0; lp<numbers.length; lp++) {
						for(int j=rp; j<numbers.length; j++) {
							if(numbers[j] <= numbers[lp] + mid) {
								rp++;
							}
							else {
								break;
							}
						}
						count += rp - lp - 1;
					}
					if(count < n*(n-1)/4) {
						start = mid + 1;
					}
					else {
						end = mid;
					}
				}
				results[i] = start;
			}
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
