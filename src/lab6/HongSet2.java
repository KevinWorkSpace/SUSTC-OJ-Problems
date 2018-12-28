package lab6;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class HongSet2 {
	
	static int[] vis;
	static boolean[] hits;
	static int[] counts; 
	static int tempCount;
	
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			Node[] tns = new Node[N+1];
			hits = new boolean[N+1];
			counts = new int[N+1];
			vis = new int[N+1];
			for(int j=1; j<N+1; j++) {
				int number = in.nextInt();
				tns[j] = new Node(number, j);
			}
			for(int j=0; j<N-1; j++) {
				int a = in.nextInt();
				int b = in.nextInt();
				tns[a].children.add(tns[b]);
				tns[b].children.add(tns[a]);
			}
			dfs(tns[1]);
			int max = 0;
			for(int j=1; j<N+1; j++) {
				if(counts[j] > max) {
					max = counts[j];
				}
			}
			results[i] = max;
		}
		for(int i=0; i<T; i++) {
			System.out.println(results[i]);
		}
	}
	
	public static int rootdfs(Node root) {
		for(int i=0; i<root.children.size(); i++) {
			tempCount = 0;
			if(root.children.get(i).val > root.val) {
				if(hits[root.children.get(i).index]) {
					if(hits[root.index]) {
						counts[root.index] += counts[root.children.get(i).index];
					}
					else {
						counts[root.index] += counts[root.children.get(i).index]+1;
						hits[root.index] = true;
					}
				}
				else {
					tempCount = rootdfs(root.children.get(i));
					if(hits[root.index]) {
						counts[root.index] += tempCount;
					}
					else {
						counts[root.index] += tempCount+1;
						hits[root.index] = true;
					}
				}
			}
		}
		if(counts[root.index] == 0) {
			counts[root.index] = 1;
			hits[root.index] = true;
		}
		return counts[root.index];
	}
	
	public static void dfs(Node root) {
		tempCount = 0;
		if(!hits[root.index]) {
			int t = rootdfs(root);
			if(!hits[root.index]) {
				counts[root.index] += t;
			}
			hits[root.index] = true;
		}
		vis[root.index] = 1;
		for(int i=0; i<root.children.size(); i++) {
			if(vis[root.children.get(i).index] == 0) {
				dfs(root.children.get(i));
			}
		}
	}
	
	static class Node {
		private int val;
		private int index;
		private ArrayList<Node> children;
		
		public Node(int val, int index) {
			this.val = val;
			this.index = index;
			children = new ArrayList<Node>();
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
