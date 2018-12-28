package lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FailedInLinearAlgebra {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
		int T = in.nextInt();
		long[][][] results = new long[T][][];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			long[][][] matrixes = new long[n][m][m];
			for(int j=0; j<n; j++) {
				for(int k=0; k<m; k++) {
					for(int p=0; p<m; p++) {
						matrixes[j][k][p] = in.nextInt();
					}
				}
			}
			String operation = in.next();
			MyStack<Character> ms = new MyStack<Character>(operation.length());
			String postfix = "";
			for(int j=0; j<operation.length(); j++) {
				char c = operation.charAt(j);
				if(c == '(') {
					ms.push(c);
				}
				else if(c == ')') {
					char temp = ' ';
					while((temp = ms.pop()) != '(') {
						postfix += String.valueOf(temp) + " ";
					}
				}
				else if(c == '+' || c == '-') {
					if(ms.isEmpty() || ms.peek() == '(') {
						ms.push(c);
					}
					else {
						while(!ms.isEmpty() && (ms.peek() == '*' || ms.peek() == '+' || ms.peek() == '-')) {
							postfix += String.valueOf(ms.pop()) + " ";
						}
						ms.push(c);
					}
				}
				else if(c == '*') {
					if (ms.isEmpty() || ms.peek() == '+'
							|| ms.peek() == '-' || ms.peek() == '(') {
						ms.push(c);
					} else {
						while (!ms.isEmpty() && ms.peek() == '*') {
							postfix += String.valueOf(ms.pop()) + " ";
						}
						ms.push(c);
					}
				}
				else {
					if(j != operation.length() - 1) {
						if(operation.charAt(j+1) == '0') {
							postfix += String.valueOf(c);
						}
						else {
							postfix += String.valueOf(c) + " ";
						}
					}
					else {
						postfix += String.valueOf(c) + " ";
					}
				}
			}
			if (!ms.isEmpty()) {
				while (!ms.isEmpty()) {
					postfix += String.valueOf(ms.pop()) + " ";
				}
			}
			MyStack<long[][]> mst = new MyStack<long[][]>(postfix.length());
			StringTokenizer st = new StringTokenizer(postfix);
			while(st.hasMoreElements()){
				String s = st.nextToken();
				if(s.equals("+")) {
					long[][] i1 = mst.pop();
					long[][] i2 = mst.pop();
					long[][] i3 = new long[m][m];
					for(int j=0; j<m; j++) {
						for(int k=0; k<m; k++) {
							long l = i1[j][k] + i2[j][k];
							i3[j][k] = l % 1000000007;
						}
					}
					mst.push(i3);
				}
				else if(s.equals("-")) {
					long[][] i1 = mst.pop();
					long[][] i2 = mst.pop();
					long[][] i3 = new long[m][m];
					for(int j=0; j<m; j++) {
						for(int k=0; k<m; k++) {
							long l = i2[j][k] - i1[j][k];
							i3[j][k] = (l + 1000000007) % 1000000007;
						}
					}
					mst.push(i3);
				}
				else if(s.equals("*")) {
					long[][] i1 = mst.pop();
					long[][] i2 = mst.pop();
					long[][] i3 = new long[m][m];
					for(int j=0; j<m; j++) {
						for(int k=0; k<m; k++) {
							i3[j][k] = 0;
							for(int q=0; q<m; q++) {
								long l = (i1[q][k] * i2[j][q]) % 1000000007;
								i3[j][k] = (i3[j][k] + l) % 1000000007;
							}
						}
					}
					mst.push(i3);
				}
				else {
					int ix = Integer.parseInt(s);
					mst.push(matrixes[ix-1]);
				}
			}
			long[][] res = mst.pop();
			results[i] = res;
		}
		for(int i=0; i<T; i++) {
			long[][] res = results[i];
			for(int j=0; j<res.length; j++) {
				for(int k=0; k<res[j].length; k++) {
					out.print(res[j][k] + " ");
				}
				out.printLine();
			}
		}
	}
	
	static class MyStack<Item> {
		private Item[] a;
		private int top;
		private int size;
		
		public MyStack(int n) {
			a = (Item[])new Object[n];
			top = -1;
			size = 0;
		}
		
		public void push(Item c) {
			if(!isFull()) {
				a[++top] = c;
				size++;
			}
		}
		
		public Item pop() {
			if(isEmpty()) {
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			top--;
			size--;
			return a[top+1];
		}
		
		public Item peek() {
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
