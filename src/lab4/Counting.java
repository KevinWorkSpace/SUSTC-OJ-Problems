package lab4;

import java.util.Scanner;

public class Counting {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		long[] result = new long[T];
		for(int i=0; i<T; i++) {
			long sum = 0;
			int n = in.nextInt();
			int m = in.nextInt();
			MyQueue mq = new MyQueue(n);
			int[] b = new int[n];
			for(int j=0; j<n; j++) {
				b[j] = in.nextInt();
			}
			for(int j=0; j<n; j++) {
				if(mq.isEmpty()) {
					mq.enQueue(b[j]);
				}
				else {
					if(b[j] - mq.peekFront() <= m) {
						mq.enQueue(b[j]);
						if(j == n-1) {
							int r = mq.getRear() - mq.getFront();
							while(r >= 2) {
								sum += r * (r - 1) / 2;
								mq.deQueue();
								r = mq.getRear() - mq.getFront();
							}
							
						}
					}
					else if(b[j] - mq.peekFront() > m){
						int r = mq.getRear() - mq.getFront();
						if(r >= 2) {
							sum += r * (r - 1) / 2;
						}
						mq.deQueue();
						if(b[j] - mq.peekFront() <= m) {
							mq.enQueue(b[j]);
							if(j == n-1) {
								r = mq.getRear() - mq.getFront();
								while(r >= 2) {
									sum += r * (r - 1) / 2;
									mq.deQueue();
									r = mq.getRear() - mq.getFront();
								}
							}
						}
						else {
							boolean flag = true;
							while(b[j] - mq.peekFront() > m) {
								r = mq.getRear() - mq.getFront();
								if(r >= 2) {
									sum += r * (r - 1) / 2;
								}
								if(mq.isEmpty()) {
									flag = false;
									mq.enQueue(b[j]);
									break;
								}
								else {
									mq.deQueue();
								}
							}
							if(flag) {
								mq.enQueue(b[j]);
								if(j == n-1) {
									r = mq.getRear() - mq.getFront();
									while(r >= 2) {
										sum += r * (r - 1) / 2;
										mq.deQueue();
										r = mq.getRear() - mq.getFront();
									}
								}
							}
						}
					}
				}
			}
			result[i] = sum;
		}
		for(int i=0; i<T; i++) {
			System.out.println(result[i]);
		}
	}
	
	static class MyQueue {
		int[] a;
		int front;
		int rear;
		int size;
		
		public MyQueue(int n) {
			size = n;
			a = new int[n];
			front = 0;
			rear = 0;
		}
		
		public int deQueue() {
			if(isEmpty()) {
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return a[front++];
			
		}
		
		public void enQueue(int i) {
			a[rear++] = i;
		}
		
		public int peekFront() {
			return a[front];
		}
		
		public int getFront() {
			return front;
		}
		
		public int getRear() {
			return rear-1;
		}
		
		public int peekRear() {
			if(isEmpty()) {
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return a[rear];
		}
		
		public boolean isEmpty() {
			return front == rear;
		}
		
		public boolean isFull() {
			if(rear == size) {
				return true;
			}
			else return false;
		}
	}
}
