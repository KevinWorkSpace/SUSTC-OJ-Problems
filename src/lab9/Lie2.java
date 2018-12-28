package lab9;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Lie2 {
	
	static ArrayList<Integer>[] human;
	static ArrayList<WolfEdge> wolf;
	static int ans=0;
	
	public static void dfs(int x){
	    int len=human[x].size();
	    for(int i=0;i<len;i++){
	        ans++;
	        dfs(human[x].get(i));
	    }
	}
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		PrintWriter out = new PrintWriter(System.out);
	    int T = in.nextInt();
	    for(int i=0; i<T; i++) {
	        int N = in.nextInt();
	        human = new ArrayList[N];
	        for(int j=0; j<N; j++) {
	        	human[j] = new ArrayList<Integer>();
	        }
	        wolf = new ArrayList<WolfEdge>();
	        UF uf = new UF(N);
	        for(int j=0; j<N; j++){
	        	int op = in.nextInt();
	            String str = in.readLine();
	            if(str.charAt(0) == 'v'){
	                human[op-1].add(j);
	                uf.union(op-1,j);
	            }
	            else{
	                wolf.add(new WolfEdge(j,op-1));
	            }
	        }
	        ans=0;
	        int sz=wolf.size();
	        for(int j=0; j<sz; j++){
	            int from = wolf.get(j).from;
	            int to = wolf.get(j).to;
	            if(uf.connected(from,to)){
	                ans++;
	                dfs(to);
	            }
	        }
	        out.println(ans);
	    }
	    out.close();
	}
	
	static class WolfEdge {
		int from;
		int to;
		
		public WolfEdge(int from, int to) {
			this.from = from;
			this.to = to;
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

		public int find(int x){
		    if(id[x] == x) return x;
		    else return id[x]=find(id[x]);
		}
		public void union(int x,int y){
		    x=find(x);
		    y=find(y);
		    if(x==y) return ;
		    else id[x]=y;
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
