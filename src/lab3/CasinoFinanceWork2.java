package lab3;

import java.util.PriorityQueue;
import java.util.Scanner;

public class CasinoFinanceWork2 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int[][] s = new int[T][];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int[] c = new int[n];
			for(int j=0; j<n; j++) {
				c[j] = in.nextInt();
			}
			int[] res = new int[c.length];
	        MaxHeap maxHeap = new MaxHeap(n/2);
			PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(n);
	        for (int j = 0; j < n; j++) {
	            if (j % 2 == 0) {
	                if (!maxHeap.isEmpty() && c[j] < maxHeap.peek()) {
	                    minHeap.offer(maxHeap.delMax());
	                    maxHeap.insert(c[j]);
	                } else {
	                    minHeap.offer(c[j]);
	                }
	                res[j] = minHeap.peek();
	            } else {
	                if (!minHeap.isEmpty() && c[j] > minHeap.peek()) {
	                    maxHeap.insert(minHeap.poll());
	                    minHeap.offer(c[j]);
	                } else {
	                    maxHeap.insert(c[j]);
	                }
	            }
	        }
	        s[i] = res;
	        
		}
		for(int i=0; i<T; i++) {
			for(int j=0; j<s[i].length; j+=2) {
				System.out.print(s[i][j] + " ");
			}
		}
		
		
	}
	static class MaxHeap {
	    private int[] array;
	    private int N = 0;
	    private int maxN;
	    public MaxHeap(int maxN) {
	        this.maxN = maxN;
	        this.array = new int[maxN+1];
	    }
	 
	    public boolean isEmpty() {
	        return N == 0;
	    }
	 
	    public int size() {
	        return N;
	    }
	 
	    public boolean isFull() {
	        return N == maxN;
	    }
	    
	    public int peek() {
	    	return array[1];
	    }
	 
	    public void insert(int v) {
	        if(isFull()) {
	            System.out.println("最大堆已满");
	            return;
	        }
	        array[++N] = v;
	        swim(N);
	    }
	 
	    public int delMax(){
	        int max = array[1];
	        exch(1,N--);
	        sink(1);
	        return max;
	 
	    }
	 
	    private boolean less(int i, int j) {
	        if((array[i] -array[j]) < 0)
	            return true;
	        else
	            return false;
	    }
	 
	    private void exch(int i, int j) {
	        int temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	    }
	    private void swim(int k) {
	        while (k > 1 && less(k/2, k)) {
	            exch(k/2, k);
	            k = k/2;
	        }
	    }
	    private void sink(int k) {
	        while (2*k <= N) {
	            int j = 2*k;
	            if(j < N && less(j, j+1))
	                j++;
	            if(!less(k, j))
	                break;
	            exch(k, j);
	            k = j;
	        }
	    }
	}

}
