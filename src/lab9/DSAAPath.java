package lab9;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class DSAAPath {
	
	static int[][] arr;
	static int index;
	
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			int M = in.nextInt();
			int K = in.nextInt();
			ArrayList<Edge>[] a = new ArrayList[N];
			for(int j=0; j<N; j++) {
				a[j] = new ArrayList<Edge>();
			}
			for(int j=0; j<M; j++) {
				int u = in.nextInt();
				int v = in.nextInt();
				int w = in.nextInt();
				Edge e1 = new Edge(v, w);
				Edge e2 = new Edge(u, w);
				a[u-1].add(e1);
				a[v-1].add(e2);
			}
			int[] subset = new int[K];
			for(int j=0; j<K; j++) {
				int x = in.nextInt();
				subset[j] = x;
			}
			int[][] ds = new int[K][];
			for(int j=0; j<K; j++) {
				ds[j] = new int[N];
				dijkstra(a, subset[j]-1, ds[j], N);
			}
			int[] d0 = new int[N];
			dijkstra(a, 0, d0, N);
			int[] dn = new int[N];
			dijkstra(a, N-1, dn, N);
			arr = new int[mul(K)][K];
			index = 0;
			arrange(subset, 0, K);
			int sp = Integer.MAX_VALUE;
			for(int j=0; j<arr.length; j++) {
				int tmp = 0;
				tmp += d0[arr[j][0]-1];
				for(int k=0; k<arr[j].length-1; k++) {
					int a1 = arr[j][k];
					int a2 = arr[j][k+1];
					int index1 = 0;
					for(int m=0; m<subset.length; m++) {
						if(subset[m] == a1) {
							index1 = m;
							break;
						}
					}
					tmp += ds[index1][a2-1];
				}
				tmp += dn[arr[j][K-1]-1];
				if(tmp < sp) {
					sp = tmp;
				}
			}
			out.println(sp);
		}
		out.close();
	}
	
	public static int mul(int K) {
		int res = 1;
		for(int i=1; i<=K; i++) {
			res *= i;
		}
		return res;
	}
	
	public static void swap(int[] str, int i, int j)
	{
		int temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}
	public static void arrange (int[] str, int st, int len)
	{
		if (st == len - 1)
		{
			for (int i = 0; i < len; i ++)
			{
				arr[index][i] = str[i];
			}
			index ++;
		}
		else
		{
			for (int i = st; i < len; i ++)
			{
				swap(str, st, i);
				arrange(str, st + 1, len);
				swap(str, st, i);
			}
		}
	}
	
	public static void dijkstra(ArrayList<Edge>[] a, int vs, int[] dist, int n) {
	    boolean[] vis = new boolean[n];
	    PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	    for (int i = 0; i < n; i++) {
	        dist[i] = Integer.MAX_VALUE;  
	    }
	    dist[vs] = 0;
	    pq.add(new Edge(vs+1, 0));
	    while(!pq.isEmpty()) {
	    	Edge e = pq.poll();
	    	if(!vis[e.v-1]) {
	    		vis[e.v-1] = true;
	    		ArrayList<Edge> arr = a[e.v-1];
	    		for(int i=0; i<arr.size(); i++) {
	    			int index = arr.get(i).v-1;
	    			if(!vis[index]) {
	    				if(dist[e.v-1] + arr.get(i).weight < dist[index]) {
	    					dist[index] = dist[e.v-1] + arr.get(i).weight;
	    					pq.add(new Edge(index+1, dist[index]));
	    				}
	    			}
	    		}
	    	}
	    }
	}
 
	static class Edge implements Comparable<Edge> {
		int v;
		int weight;
		
		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge e) {
			if(this.weight > e.weight) {
				return 1;
			}
			else if(this.weight < e.weight) {
				return -1;
			}
			else {
				return 0;
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
