package lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ConnectedGroup {
	
	static int[][] vis;
	
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] grids = new int[n][m];
			vis = new int[n][m];
			for(int j=0; j<n; j++) {
				for(int k=0; k<m; k++) {
					grids[j][k] = in.nextInt();
				}
			}
			int count = 0;
			for(int j=0; j<n; j++) {
				for(int k=0; k<m; k++) {
					if(vis[j][k] == 0) {
						dfs(grids, j, k);
						count ++;
					}
				}
			}
			results[i] = count;
		}
		for(int i=0; i<T; i++) {
			System.out.println(results[i]);
		}
	}
	
	public static void dfs(int[][] grids, int j, int k) {
		vis[j][k] = 1;
		int up = 0;
		int down = 0;
		int left = 0;
		int right = 0;
		int color = grids[j][k];
		if(j == 0) {
			up = 0;
		}
		else {
			up = grids[j-1][k];
			if(up == color && vis[j-1][k] == 0) {
				dfs(grids, j-1, k);
			}
		}
		if(j == grids.length-1) {
			down = 0;
		}
		else {
			down = grids[j+1][k];
			if(down == color && vis[j+1][k] == 0) {
				dfs(grids, j+1, k);
			}
		}
		if(grids[0].length == 1) {
			left = 0;
			right = 0;
		}
		else {
			if(k == 0) {
				left = grids[j][grids[0].length-1];
				if(left == color && vis[j][grids[0].length-1] == 0) {
					dfs(grids, j, grids[0].length-1);
				}
				right = grids[j][1]; 
				if(right == color && vis[j][1] == 0) {
					dfs(grids, j, 1);
				}
			}
			else if(k == grids[0].length-1) {
				left = grids[j][k-1];
				if(left == color && vis[j][k-1] == 0) {
					dfs(grids, j, k-1);
				}
				right = grids[j][0];
				if(right == color && vis[j][0] == 0) {
					dfs(grids, j, 0);
				}
			}
			else {
				left = grids[j][k-1];
				if(left == color && vis[j][k-1] == 0) {
					dfs(grids, j, k-1);
				}
				right = grids[j][k+1];
				if(right == color && vis[j][k+1] == 0) {
					dfs(grids, j, k+1);
				}
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
