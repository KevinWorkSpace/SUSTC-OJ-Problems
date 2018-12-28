package lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class NearestPair {
	
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int T = in.nextInt();
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int K = in.nextInt();
			ArrayList<Integer>[] a = new ArrayList[n];
			Queue<Integer> q = new ArrayDeque<Integer>();
			int[] vis = new int[n];
			long[] length = new long[n];
			for(int j=0; j<n; j++) {
				a[j] = new ArrayList<Integer>();
			}
			for(int j=0; j<m; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				a[x-1].add(y);
				a[y-1].add(x);
			}
			for(int j=0; j<K; j++) {
				int k = in.nextInt();
				q.add(k);
				vis[k-1] = k;
			}
			long shortestPath = 0;
			boolean flag = true;
			while(!q.isEmpty()) {
				int top = q.poll();
				for(int j=0; j<a[top-1].size(); j++) {
					int current = a[top-1].get(j);
					if(vis[current-1] == 0) {
						vis[current-1] = vis[top-1]; 
						q.add(current);
						length[current-1] = length[top-1] + 1;
					}
					else if(vis[current-1] != vis[top-1]) {  
						shortestPath = length[current-1] + length[top-1] + 1;
						flag = false;
						break;
					}
				}
				if(!flag) {
					break;
				}
			}
			if(flag) {
				System.out.println(-1);
			}
			else {
				System.out.println(shortestPath);
			}
		}
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
