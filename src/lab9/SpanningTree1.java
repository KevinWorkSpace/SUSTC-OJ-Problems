package lab9;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class SpanningTree1 {
	
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			int M = in.nextInt();
			Edge[] E = new Edge[M];
			for(int j=0; j<M; j++) {
				int u = in.nextInt();
				int v = in.nextInt();
				int w = in.nextInt();
				E[j] = new Edge(u, v, w);
			}
			KruskalMst kmst = new KruskalMst(E, N);
			int result = 0;
			for(Edge e : kmst.mst) {
				result += e.weight;
			}
			out.println(result);
		}
		out.close();
	}
	
	static class KruskalMst {
	    public Queue<Edge> mst;
	    public KruskalMst(Edge[] edges, int N){
	        mst = new ArrayDeque<Edge>();
	        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	        for(int i=0; i<edges.length; i++) {
	        	pq.add(edges[i]);
	        }
	        UF uf = new UF(N);
	        while(!pq.isEmpty() && mst.size() < N-1){
	            Edge e = pq.poll();
	            int u = e.u;
	            int v = e.v;
	            if(uf.connected(u-1, v-1)) {
	            	continue;
	            }
	            uf.union(u-1, v-1);
	            mst.add(e);
	        }
	    }
	}
	
	static class UF {
		private int[] id; 
		private int count; 
	 
		public UF(int N) {
			count = N;
			id = new int[N];
			for (int i = 0; i < N; i++) {
				id[i] = i;
			}
		}
		public int count() {
			return count;
		}
		public boolean connected(int a, int b) {
			return find(a) == find(b);
		}
		private int find(int a) {
			return id[a];
		}
		private void union(int a, int b) {
			if(find(a)!=find(b)){
				int tmp = id[a];
				for(int i=0; i<id.length; i++){
					if(id[i] == tmp) {
						id[i] = id[b];
					}
				}
				count--;
			}
			else{
				return;
			}
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int u;
		int v;
		int weight;
		
		public Edge(int u, int v, int weight) {
			this.u = u;
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
