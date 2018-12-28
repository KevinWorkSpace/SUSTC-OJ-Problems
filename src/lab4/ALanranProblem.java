package lab4;

import java.util.Scanner;

public class ALanranProblem {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		in.nextLine();
		boolean[] result = new boolean[T];
		for(int i=0; i<T; i++) {
			MyStack ms = new MyStack(6);
			String str = "lanran";
			for(int j=0; j<str.length(); j++) {
				ms.push(str.charAt(str.length()-1-j));
			}
			String test = in.nextLine();
			boolean flag = true;
			for(int j=0; j<test.length(); j++) {
				if(test.charAt(j) == ms.peek()) {
					ms.pop();
					if(ms.isEmpty()) {
						flag = false;
						break;
					}
				}
			}
			if(flag) {
				result[i] = false;
			}
			else result[i] = true;
		}
		for(int i=0; i<T; i++) {
			if(result[i]) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
	}
	
	static class MyStack {
		private char[] a;
		private int top;
		private int size;
		
		public MyStack(int n) {
			a = new char[n];
			top = -1;
			size = 0;
		}
		
		public void push(char c) {
			if(!isFull()) {
				a[++top] = c;
				size++;
			}
		}
		
		public void pop() {
			if(!isEmpty()) {
				top--;
				size--;
			}
		}
		
		public char peek() {
			if(isEmpty()) {
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return a[top];
		}
		
		public boolean isEmpty() {
			if(top == -1) {
				return true;
			}
			else return false;
		}
		
		public boolean isFull() {
			if(size == a.length) {
				return true;
			}
			else return false;
		}
	}
}
