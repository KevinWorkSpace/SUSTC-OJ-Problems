package lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class ExcitingSpider {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
		int T = in.nextInt();
		int[][] result = new int[T][];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			MyStack ms = new MyStack(n);
			int[] res = new int[n];
			int num = 0;
			int[] r = new int[n];
			for(int j=0; j<n; j++) {
				r[j] = in.nextInt();
			}
			MyStack sequence = new MyStack(n);
			for(int j=0; j<n; j++) {
				sequence.push(r[n-1-j]);
			}
			while(!sequence.isEmpty()) {
				int minInRemain = sequence.getMin();
				int card = sequence.peek();
				if(card == minInRemain) {
					if(!ms.isEmpty()) {
						if(ms.peek() < minInRemain) {
							res[num++] = ms.pop();
						}
						else {
							sequence.pop();
							res[num++] = card;
						}
					}
					else {
						sequence.pop();
						res[num++] = card;
					}
				}
				else {
					if(ms.isEmpty()) {
						ms.push(card);
						sequence.pop();
					}
					else {
						if(ms.peek() < minInRemain) {
							res[num++] = ms.pop();
						}
						else {
							sequence.pop();
							ms.push(card);
						}
					}
				}
			}
			while(!ms.isEmpty()) {
				res[num++] = ms.pop();
			}
			result[i] = res;
		}
		for(int i=0; i<T; i++) {
			for(int j=0; j<result[i].length; j++) {
				if(result[i][j] == 0) {
					break;
				}
				else {
					out.print(result[i][j] + " ");
				}
			}
			out.printLine();
		}
	}
	
	static class MyStack {
		private int[] a;
		private int top;
		private int size;
		private Stack<Integer> minStack;
		
		public MyStack(int n) {
			a = new int[n];
			top = -1;
			size = 0;
			minStack = new Stack<Integer>();
		}
		
		public void push(int c) {
			if(isEmpty()) {
				minStack.push(c);
			}
			else {
				if(c <= minStack.peek()) {
					minStack.push(c);
				}
			}
			if(!isFull()) {
				a[++top] = c;
				size++;
			}
		}
		
		public int pop() {
			if(isEmpty()) {
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(this.peek() == minStack.peek()) {
				minStack.pop();
			}
			top--;
			size--;
			return a[top+1];
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
		
		public int getMin() {
			if(minStack.isEmpty()) {
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return minStack.peek();
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
