package lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UniversityHotel {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		while(in.hasNext()) {
			int n = in.nextInt();  
			int m = in.nextInt();  
			int[] rooms = new int[n+1]; 
			for(int i=1; i<n+1; i++) {
				rooms[i] = in.nextInt();
			}
			int[][] cp = new int[m][3];
			for(int i=0; i<cp.length; i++) {
				cp[i][0] = in.nextInt();
				cp[i][1] = in.nextInt();
				cp[i][2] = in.nextInt();
			}
			int start = 1;
			int end = m;
			while(start <= end) {
				int[] c = new int[n+1];  
				int mid = (start + end) / 2;
				int[] s = new int[n+1];
				for(int i=0; i<mid; i++) {
					int d = cp[i][0];
					int l = cp[i][1];
					int r = cp[i][2];
					c[l] += d;
					if(r+1 <= n) c[r+1] -= d;
				}
				boolean flag = true;
				for(int j=1; j<n+1; j++) {
					s[j] = s[j-1] + c[j];
					if(s[j] > rooms[j]) {
						flag = false;
						break;
					}
				}
				if(!flag) {
					end = mid - 1;
				}
				else {
					start = mid + 1;
				}
			}
			if(start == m+1) System.out.println(0);
			else {
				System.out.println(-1);
				System.out.println(start);
			}
		}
	}
	static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch(IOException e) {
                return false;
            }
        } 

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
		 public String next() {
	         while (tokenizer == null || !tokenizer.hasMoreTokens()) {
	             try {
	                 tokenizer = new StringTokenizer(reader.readLine());
	             } catch (IOException e) {
	                 throw new RuntimeException(e);
	             }
	         }
	         return tokenizer.nextToken();
	     }
	
	     public int nextInt() {
	         return Integer.parseInt(next());
	     }
	}
}
