package lab5;

import java.util.Scanner;

public class KateMakeProgram {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			in.nextLine();
			String S = in.nextLine();
			int m = in.nextInt();
			in.nextLine();
			String P = in.nextLine();
			int[] next = new int[m];
			next = getNext(P, next);
			int count = kmpSearch(S, P, next);
			results[i] = count;
		}
		for(int i=0; i<T; i++) {
			System.out.println(results[i]);
		}
	}
	
	public static int kmpSearch(String s, String p, int[] next) {
		int i = 0;
		int j = 0;
		int count = 0;
		while (i < s.length()) {
			if (j == -1 || s.charAt(i) == p.charAt(j)) {
				i++;
				j++;
				if(j == p.length()) {
					i--;
					j = next[j-1];
					count++;
				}
			}
			else {
				j = next[j];
			}
		}
		return count;
	}
	
	public static int[] getNext(String p, int next[]) {
		int pLen = p.length();
		next[0] = -1;
		int k = -1;
		int j = 0;
		while (j < pLen - 1)
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
