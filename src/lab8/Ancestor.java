package lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Ancestor {
	
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			ArrayList<Integer>[] a = new ArrayList[n];
			for(int j=0; j<n; j++) {
				a[j] = new ArrayList<Integer>();
			}
			boolean[] sourses = new boolean[n];
			//建图
			for(int j=0; j<n-1; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				//x入度不为0
				sourses[x-1] = true;
				a[y-1].add(x);
			}
			int root = 0;
			//判断根的位置
			for(int j=0; j<n; j++) {
				if(!sourses[j]) {
					root = j;
				}
			}
			int count = 1;
			int[] vis = new int[n];
			int[] d_tm = new int[n];
			int[] f_tm = new int[n];
			Stack<Integer> s = new Stack<Integer>();
		    s.push(root);
		    while (!s.isEmpty()) {
		        int top = s.peek();
		        if(vis[top] == 0) {
		        	vis[top] = 1;
		        	d_tm[top] = count++;
		        	for(int j=0; j<a[top].size(); j++) {
		        		int current = a[top].get(j);
		        		s.push(current-1);
		        	}
		        }
		        else {
		        	int t = s.pop();
		        	f_tm[t] = count++;
		        }
	        }
			for(int j=0; j<m; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				if(d_tm[y-1] <= d_tm[x-1] && f_tm[y-1] >= f_tm[x-1]) {
					out.println("Yes");
				}
				else {
					out.println("No");
				}
			}
		}
		 out.close();
	}
	
	static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
 
        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c;
			c = read();
			 while (c <= ' ')
	                c = read();
	            boolean neg = (c == '-');
	            if (neg)
	                c = read();
	            do
	            {
	                ret = ret * 10 + c - '0';
	            }  while ((c = read()) >= '0' && c <= '9');
	            if (neg)
	            	return -ret;
			return ret;
        }
 
        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
 
        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
 
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
 
            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }
 
            if (neg)
                return -ret;
            return ret;
        }
 
        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}
