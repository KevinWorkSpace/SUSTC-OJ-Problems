package lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class ReachabilityTesting {
	
	public static void main(String[] args) {
        Reader in = new Reader();
		int T = in.nextInt();
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] a = new int[n][n];
			for(int j=0; j<m; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				a[x-1][y-1] = 1;
			}
			int Q = in.nextInt();
			int[] arr = new int[n];
			Queue<Integer> q = new LinkedList<Integer>();
			for(int j=0; j<Q; j++) {
				q.clear();
				for(int l=0; l<n; l++) {
					arr[l] = 0;
				}
				int x = in.nextInt();
				int y = in.nextInt();
				q.add(x);
				arr[x-1] = 1;
				boolean flag = false;
				while(!q.isEmpty()) {
			        int top = q.poll();
			        for(int k=0; k<n; k++) {
			        	if(a[top-1][k] == 1 && arr[k] == 0) {
			        		arr[k] = 1;
			        		if(k == y-1) {
			        			flag = true;
			        			break;
			        		}
			        		q.add(k+1);
			        	}
			        }
			        if(flag) {
			        	break;
			        }
			    }
				if(flag) {
					System.out.println("YES");
				}
				else {
					System.out.println("NO");
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
 
        public int nextInt()
        {
            int ret = 0;
            byte c;
			try {
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
			} catch (IOException e) {
				e.printStackTrace();
			}
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
