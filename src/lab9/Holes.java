package lab9;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Holes {
	
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			Node[] nodes = new Node[N + 2];
			for(int j=0; j<N; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				int z = in.nextInt();
				int r = in.nextInt();
				nodes[j] = new Node(j, x, y, z, r);
			}
			int Sx = in.nextInt();
			int Sy = in.nextInt();
			int Sz = in.nextInt();
			nodes[N] = new Node(N, Sx, Sy, Sz, 0);
			int Tx = in.nextInt();
			int Ty = in.nextInt();
			int Tz = in.nextInt();
			nodes[N+1] = new Node(N+1, Tx, Ty, Tz, 0);
			ArrayList<Edge>[] a = new ArrayList[N+2];
			for(int j=0; j<N+2; j++) {
				a[j] = new ArrayList<Edge>();
			}
			for(int j=0; j<nodes.length; j++) {
				for(int k=0; k<nodes.length; k++) {
					if(j != k) {
						a[j].add(new Edge(nodes[j], nodes[k]));
					}
				}
			}
			double[] ds = new double[N+2];
			dijkstra(nodes, a, N, ds, N+2);
			out.println(Math.round(ds[N+1]));
		}
		out.close();
	}
	
	static class Node {
		int index;
		int x;
		int y;
		int z;
		int r;
		public Node(int index, int x, int y, int z, int r) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.z = z;
			this.r = r;
		}
	}
	
	static class Edge {
		Node u;
		Node v;
		double weight;
		
		public Edge(Node u, Node v) {
			this.u = u;
			this.v = v;
			double dis = 100*(Math.sqrt(Math.pow(u.x-v.x, 2)+Math.pow(u.y-v.y, 2)+Math.pow(u.z-v.z, 2))-u.r-v.r);
			if(dis < 0) {
				weight = 0;
			}
			else {
				weight = dis;
			}
		}
	}
	
	static class Info implements Comparable<Info> {
		Node v;
		double dist;
		
		public Info(Node v, double dist) {
			this.v = v;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Info e) {
			if(this.dist > e.dist) {
				return 1;
			}
			else if(this.dist < e.dist) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	
	public static void dijkstra(Node[] nodes, ArrayList<Edge>[] a, int vs, double[] dist, int n) {
	    boolean[] vis = new boolean[n];
	    PriorityQueue<Info> pq = new PriorityQueue<Info>();
	    for (int i = 0; i < n; i++) {
	        dist[i] = Integer.MAX_VALUE;  
	    }
	    dist[vs] = 0;
	    pq.add(new Info(nodes[vs], 0));
	    while(!pq.isEmpty()) {
	    	Info e = pq.poll();
	    	if(!vis[e.v.index]) {
	    		vis[e.v.index] = true;
	    		ArrayList<Edge> arr = a[e.v.index];
	    		for(int i=0; i<arr.size(); i++) {
	    			int index = arr.get(i).v.index;
	    			if(!vis[index]) {
	    				if(dist[e.v.index] + arr.get(i).weight < dist[index]) {
	    					dist[index] = dist[e.v.index] + arr.get(i).weight;
	    					pq.add(new Info(nodes[index], dist[index]));
	    				}
	    			}
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
