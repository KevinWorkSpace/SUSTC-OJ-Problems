package lab9;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Lie {
	
	static int[] vis;
	static ArrayList<Integer> trace = new ArrayList<Integer>();
	static ArrayList<ArrayList<Integer>> cycles;
	
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			ArrayList<Integer>[] a = new ArrayList[N];
			int[] says = new int[N];  
			for(int j=0; j<N; j++) {
				a[j] = new ArrayList<Integer>();
			}
			for(int j=0; j<N; j++) {
				int x = in.nextInt();
				String s = in.readLine();
				a[j].add(x);
				if(s.equals("werewolf\r")) {
					says[j] = 1;
				}
				else {
					says[j] = 0;
				}
			}
			vis = new int[N];
			cycles = new ArrayList<ArrayList<Integer>>();
			trace = new ArrayList<Integer>();
			ArrayList<Integer> wolves = new ArrayList<Integer>();
			findCycle(a, 0);
			for(int j=0; j<cycles.size(); j++) {
				ArrayList<Integer> curr_cycle = cycles.get(j);
				int cnt = 0;
				int index = 0;
				for(int k=0; k<curr_cycle.size(); k++) {
					int node = curr_cycle.get(k);
					if(says[node] == 1) {
						cnt++;
						index = a[node].get(0)-1;
					}
					if(cnt > 1) {
						break;
					}
				}
				if(cnt == 1) {
					wolves.add(index);
					searchWolf(says, wolves, a, index);
				}
			}
			out.println(wolves.size());
		}
		out.close();
	} 
	
	public static void searchWolf(int[] says, ArrayList<Integer> wolves, ArrayList<Integer>[] a, int index) {
		for(int k=0; k<a.length; k++) {
			if(a[k].contains(index+1) && says[k] == 0) {
				wolves.add(k);
				searchWolf(says, wolves, a, k);
			}
		}
	}
	
	public static void findCycle(ArrayList<Integer>[] a, int v) {  
        if(vis[v]==1) {  
            int j;  
            if((j=trace.indexOf(v))!=-1) {  
            	ArrayList<Integer> ar = new ArrayList<Integer>();
            	while(j < trace.size()) {
            		ar.add(trace.get(j));
            		j++;
            	}
            	cycles.add(ar);
                return;  
            }  
            return;  
        }  
        vis[v]=1;  
        trace.add(v);  
        for(int i=0; i<a[v].size(); i++) {  
            findCycle(a, a[v].get(i)-1);  
        }  
        trace.remove(trace.size()-1);  
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
