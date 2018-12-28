package lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class LongestPath {
	
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int T = in.nextInt();
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			ArrayList<Edge>[] a = new ArrayList[n];
			for(int j=0; j<n; j++) {
				a[j] = new ArrayList<Edge>();
			}
			boolean[] sourses = new boolean[n];
			for(int j=0; j<m; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				int z = in.nextInt();
				Edge e = new Edge(y, z);
				a[x-1].add(e);
				sourses[y-1] = true;
			}
			long longestPath = 0;
			long[] distTo = new long[n];
			int[] tops = topologicalSort(n, a);
			for(int j=0; j<n; j++) {
			   if(!sourses[j]) {
				   for(int v = 0; v < n; v++) {
					   distTo[v] = Integer.MIN_VALUE;
				   }
			       distTo[j] = 0;
			       for(int l=0; l<n; l++) {
						int tmp = tops[l] - 1;
						for(int k=0; k<a[tmp].size(); k++) {
							int w = a[tmp].get(k).w - 1;
							int weight = a[tmp].get(k).weight;
							if (distTo[w] < distTo[tmp] + weight) {
							      distTo[w] = distTo[tmp] + weight;
							}
						}
			       }
			       long tmp = 0;
			       for(int k=0; k<n; k++) {
			    	   if(distTo[k] > tmp) {
			    		   tmp = distTo[k];
					   }
				   }
				   if(tmp > longestPath) {
					   longestPath = tmp;
				   }
			   }
		   }
		   System.out.println(longestPath);
		}
	}
	
	static class Edge {
	    int w;
	    int weight;
	    public Edge(int w, int weight) {
	        this.w = w;
	        this.weight = weight;
	    }
	    public int to() {
	        return w;
	    }
	    public int weight() {
	        return weight;
	    }
	}
	
	public static int[] topologicalSort(int n, ArrayList<Edge>[] a) {
	    int index = 0;
	    int[] ins = new int[n];              
	    int[] tops = new int[n];             
	    Queue<Integer> queue = new ArrayDeque<Integer>();
	    for(int i = 0; i < n; i++) {
	    	for(int j=0; j<a[i].size(); j++) {
	    		int tmp = a[i].get(j).w;
	    		ins[tmp-1]++;
	    	}
	    }
	    for(int i = 0; i < n; i ++)
	        if(ins[i] == 0)
	            queue.offer(i);                 
	    while (!queue.isEmpty()) {             
	        int j = queue.poll();            
	        tops[index++] = j + 1;                
	        ArrayList<Edge> ai = a[j];
	        for(int k=0; k<ai.size(); k++) {
	        	ins[ai.get(k).w - 1]--;
	        	if(ins[ai.get(k).w - 1] == 0) {
	        		queue.offer(ai.get(k).w - 1);   
	        	}
	        }
	    }
	    return tops;
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
