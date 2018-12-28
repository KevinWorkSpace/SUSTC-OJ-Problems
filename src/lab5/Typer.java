package lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Typer {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int n = in.nextInt();
		StringBuffer[] text = new StringBuffer[n];
		StringBuffer[] textCurrent = new StringBuffer[n];
		int[][] nexts = new int[n][]; 
		Stack<Integer>[] ks = (Stack<Integer>[])new Stack[n];
		for(int i=0; i<ks.length; i++) {
			ks[i] = new Stack<Integer>();
		}
		Stack<Integer>[] js = (Stack<Integer>[])new Stack[n];
		for(int i=0; i<js.length; i++) {
			js[i] = new Stack<Integer>();
		}
		Stack<Integer>[] nxts = (Stack<Integer>[])new Stack[n];
		for(int i=0; i<n; i++) {
			nxts[i] = new Stack<Integer>();
		}
		for(int i=0; i<n; i++) {
			text[i] = new StringBuffer(in.next() + "*");
			textCurrent[i] = new StringBuffer(text[i]);
		}
		String operation = in.next() + "/";
		for(int i=0; i<nexts.length; i++) {
			nexts[i] = new int[text[i].length() + operation.length()];
		}
		for(int i=0; i<text.length; i++) {
			int pLen = text[i].length();
			nexts[i][0] = -1;
			int k = -1;
			int j = 0;
			while (j < pLen - 1)
			{
				if (k == -1 || text[i].charAt(j) == text[i].charAt(k))
				{
					++j;
					++k;
					if (text[i].charAt(j) != text[i].charAt(k))
						nexts[i][j] = k;   
					else
						nexts[i][j] = nexts[i][k];
				}
				else
				{
					k = nexts[i][k];
				}
			}
			ks[i].push(k);
			js[i].push(j);
		}
		StringBuffer smallString = text[0];
		for(int i=1; i<n; i++) {
			if(text[i].length() < smallString.length()) {
				smallString = text[i];
			}
		}
		int remainC = smallString.length() - 1;
		System.out.println(remainC);
		for(int j=0; j<operation.length()-1; j++) {
			boolean dflag = false;
			if(operation.charAt(j) != '-') {
				for(int i=0; i<n; i++) {
					textCurrent[i].append(operation.charAt(j));
				}
			}
			else {
				for(int i=0; i<n; i++) {
					if(textCurrent[i].length() > text[i].length()) {
						textCurrent[i].deleteCharAt(textCurrent[i].length() - 1);
						dflag = true;
					}
				}
			}
			int rc = smallString.length() - 1;
			for(int i=0; i<text.length; i++) {
				int commonCount = 0;
				if(dflag) {
					ks[i].pop();
					js[i].pop();
					nxts[i].pop();
					if(!nxts[i].isEmpty()) {
						commonCount = nxts[i].peek();
					}
					else {
						commonCount = 0;
					}
				}
				else {
					int k = ks[i].peek();
					int g = js[i].peek();
					while (g < textCurrent[i].length())
					{
						if (k == -1 || textCurrent[i].charAt(g) == textCurrent[i].charAt(k))
						{
							++g;
							++k;
							if (g != textCurrent[i].length() && textCurrent[i].charAt(g) != textCurrent[i].charAt(k)) {
								nexts[i][g] = k;  
							}
							else
								nexts[i][g] = nexts[i][k];
						}
						else
						{
							k = nexts[i][k];
						}
					}
					ks[i].push(k);
					js[i].push(g);
					nxts[i].push(k);
					commonCount = k;
				}
				int remain = text[i].length() - 1;
				if(remain - commonCount < rc) {
					rc = remain - commonCount;
				}
			}
			System.out.println(rc);
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
