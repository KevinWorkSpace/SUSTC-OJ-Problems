package lab4;

import java.util.Scanner;

public class BracketsMatching {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		boolean[] result = new boolean[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			in.nextLine();
			MyStack ms = new MyStack(n);
			String str = in.nextLine();
			boolean flag = true;
			for(int j=0; j<str.length(); j++) {
				char c = str.charAt(j);
				if(c == '(' || c == '[' || c == '{') {
					ms.push(c);
				}
				else if(c == ')') {
					if(!ms.isEmpty()) {
						if(ms.pop() != '(') {
							flag = false;
							break;
						}
					}
					else {
						flag = false;
						break;
					}
				}
				else if(c == ']') {
					if(!ms.isEmpty()) {
						if(ms.pop() != '[') {
							flag = false;
							break;
						}
					}
					else {
						flag = false;
						break;
					}
				}
				else if(c == '}') {
					if(!ms.isEmpty()) {
						if(ms.pop() != '{') {
							flag = false;
							break;
						}
					}
					else {
						flag = false;
						break;
					}
				}
			}
			if(flag && ms.isEmpty()) {
				result[i] = true;
			}
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
		
		public char pop() {
			if(isEmpty()) {
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			size--;
			return a[top--];
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
