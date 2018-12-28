package lab5Bonus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TheKthSmallestNumber {
	
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
			MergeSort(numbers, 0, N-1);
			results[i] = numbers[k-1];
		}
		for(int i=0; i<T; i++) {
			System.out.println(results[i]);
		}
	}
	
	public static void MergeSort(int[] arr, int low, int high) {
        int mid = (low + high)/2;
        if(low < high) {
            MergeSort(arr, low, mid);
            MergeSort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }
    
    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid+1;
        int k = 0;
        for(; i <= mid && j <= high; k++) {
            if(arr[i] < arr[j])
                temp[k] = arr[i++];
            else
                temp[k] = arr[j++];
        }
        while(i <= mid)
            temp[k++] = arr[i++];
        while(j <= high)
            temp[k++] = arr[j++];
        for(int l = 0; l < temp.length; l++)
            arr[low + l] = temp[l];
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
