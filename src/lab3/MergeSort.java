package lab3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MergeSort {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
		int T = in.nextInt();
		int[][] cc = new int[T][];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[] a = new int[n];
			int[] b = new int[m];
			for(int j=0; j<a.length; j++) {
				a[j] = in.nextInt();
			}
			for(int j=0; j<b.length; j++) {
				b[j] = in.nextInt();
			}
			int[] c = new int[m+n];
			int p = 0;
			int q = 0;
			int num = 0;
			while(p < a.length && q < b.length) {
				if(a[p] < b[q]) {
					c[num++] = a[p++];
				}
				else c[num++] = b[q++];
			}
			while(p < a.length) {
				c[num++] = a[p++];
			}
			while(q < b.length) {
				c[num++] = b[q++];
			}
			cc[i] = c;
		}
		for(int i=0; i<cc.length; i++) {
			for(int j=0; j<cc[i].length; j++) {
				out.print(cc[i][j] + " ");
			}
			out.printLine();
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

