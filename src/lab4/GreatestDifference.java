package lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class GreatestDifference {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
		int T = in.nextInt();
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			ArrayList<Integer> a = new ArrayList<Integer>();
			MyStack ms = new MyStack(n);
			for(int j=0; j<n; j++) {
				String op = in.next();
				if(op.equals("push")) {
					int value = in.nextInt();
					ms.push(value);
				}
				else {
					ms.pop();
					a.add(ms.getDifference());
				}
			}
			for(int k : a) {
				System.out.println(k);
			}
		}
	}
	
	static class MyStack {
		private int[] a;
		private int top;
		private int size;
		private Stack<Integer> maxStack;
		private Stack<Integer> minStack;
		
		public MyStack(int n) {
			a = new int[n];
			top = -1;
			size = 0;
			maxStack = new Stack<Integer>();
			minStack = new Stack<Integer>();
		}
		
		public void push(int c) {
			if(isEmpty()) {
				maxStack.push(c);
				minStack.push(c);
			}
			else {
				if(c <= minStack.peek()) {
					minStack.push(c);
				}
				else if(c >= maxStack.peek()) {
					maxStack.push(c);
				}
			}
			if(!isFull()) {
				a[++top] = c;
				size++;
			}
		}
		
		public void pop() {
			if(!isEmpty()) {
				if(this.peek() == minStack.peek()) {
					minStack.pop();
				}
				if(this.peek() == maxStack.peek()) {
					maxStack.pop();
				}
				top--;
				size--;
			}
		}
		
		public int peek() {
			if(isEmpty()) {
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return a[top];
		}
		
		public int getDifference() {
			if(isEmpty()) {
				return 0;
			}
			else {
				return maxStack.peek() - minStack.peek();
			}
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
	
	static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)), true);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
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
