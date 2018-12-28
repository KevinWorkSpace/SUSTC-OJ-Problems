package lab5Bonus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TheKthSmallestNumber2 {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			int k = in.nextInt();
			int[] numbers = new int[N];
			for(int j=0; j<N; j++) {
				numbers[j] = in.nextInt();
			}
			results[i] = quickSelect(numbers, 0, numbers.length-1, k);
		}
		for(int i=0; i<T; i++) {
			System.out.println(results[i]);
		}
	}
	
	public static int quickSelect(int[] arr, int left, int right, int k) {
		int i = left;
		int j = right;
		while(i < j) {
			while(i < j && i < right && arr[i] <= arr[left]) {
				i++;
			}
			while(i < j && j > left && arr[j] >= arr[left]) {
				j--;
			}
			swap(arr, i, j);
		}
		swap(arr, i, left);
		if(i - left + 1 == k) {
			return arr[i];
		}
		else if(i - left + 1 > k){
			return quickSelect(arr, left, i-1, k);
		}
		else {
			return quickSelect(arr, i+1, right, k - (i - left + 1));
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[i] = temp;
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
