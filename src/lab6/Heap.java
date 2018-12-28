package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Heap {
	
	static class MinHeap {
		
		private int capacity;
		private int maxCapacity;
		public int[] data;
		
		public MinHeap(int capacity, int maxCapacity) {
			this.capacity = capacity;
			this.maxCapacity = maxCapacity;
			data = new int[maxCapacity+1];
		}
		
		public void shiftDown(int k){
			while(2*k <= capacity){     
				 int j = 2 * k;      
				 if(j+1 <= capacity && data[j+1] < data[j]){ 
					 j += 1;
				 }
				 if(data[k] <= data[j])
					 break;
				 swap(k, j);
				 k=j;       
			}
		}
		
		public void shiftUp(int i) {
	        if (i == 1) return;
	        while (i != 1) {
		        if (data[i] < data[i/2]) {
		           swap(i, i/2);
		        } 
		        else {
		           break;
		        }
		        i = i / 2;
	        }
	    }
		
		public void insert(int i) {
			data[++capacity] = i;
			shiftUp(capacity);
		}
		
		public int deleteMin() {
			if(capacity > 0) {
				data[1] = data[capacity];
				data[capacity--] = 0;
				shiftDown(1);
			}
			return data[1];
		}
		
		public void swap(int k, int j) {
			int temp = data[k];
			data[k] = data[j];
			data[j] = temp;
		}
	}
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		ArrayList[] results = new ArrayList[T];
		for(int i=0; i<T; i++) {
			results[i] = new ArrayList<Integer>();
		}
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int[] initArr = new int[n];
			for(int j=0; j<n; j++) {
				initArr[j] = in.nextInt();
			}
			int q = in.nextInt();
			MinHeap h = new MinHeap(0, n+q);
			for(int j=0; j<n; j++) {
				h.insert(initArr[j]);
			}
			for(int j=0; j<q; j++) {
				int opNum = in.nextInt();
				if(opNum == 1) {
					int num = in.nextInt();
					h.insert(num);
				}
				else if(opNum == 2) {
					h.deleteMin();
				}
				else {
					results[i].add(h.data[1]);
				}
			}
		}
		for(int i=0; i<T; i++) {
			for(int j=0; j<results[i].size(); j++) {
				System.out.println(results[i].get(j));
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
