package lab5;

import java.util.Scanner;

public class Presuffix {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int[] rN = new int[T];
		StringBuilder[] rS = new StringBuilder[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			String s = in.next();
			String t = in.next();
			String sAndt = s + "b" + t + "a";
			int[] next = new int[n + m + 2];
			next = getNext(sAndt, next);
			StringBuilder str = new StringBuilder("");
			rN[i] = next[next.length - 1];
			for(int j=0; j<rN[i]; j++) {
				str.append(s.charAt(j));
			}
			rS[i] = str;
		}
		for(int i=0; i<T; i++) {
			System.out.println(rN[i] + " " + rS[i]);
		}
	}
	
	public static int[] getNext(String p, int next[]) {
		next[0] = -1;
		int k = -1;
		int j = 0;
		while (j < p.length() - 1)
		{
			if (k == -1 || p.charAt(j) == p.charAt(k)) 
			{
				++k;
				++j;
				next[j] = k;
			}
			else 
			{
				k = next[k];
			}
		}
		return next;
	}
}
