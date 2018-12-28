package lab5;

import java.util.Scanner;

public class ASkrSong {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			in.nextLine();
			String s = in.nextLine();
			String s1 = s.substring(0, s.length()/3);
			String s2 = s.substring(s.length()-s.length()/3, s.length());
			String sm = s.substring(s.length()/3, 2*s.length()/3);
			String str = s1 + "*" + s2 + "/";
			int[] next = new int[str.length()];
			next = getNext(str, next);
			StringBuffer sb = new StringBuffer("");
			for(int j=0; j<next[next.length - 1]; j++) {
				sb.append(s1.charAt(j));
			}
			if(KmpSearch(sm, sb, next) != -1) {
				results[i] = next[next.length-1];
			}
			else {
				String st = s1;
				int[] nxt = new int[st.length() + 1];
				nxt = getNext(st + "*", nxt);
				while(nxt[nxt.length-1] != 0) {
					StringBuffer newPattern = new StringBuffer("");
					for(int j=0; j<nxt[nxt.length-1]; j++) {
						newPattern.append(st.charAt(j));
					}
					String mid = s.substring(nxt[nxt.length-1], s.length()-nxt[nxt.length-1]);
					if(KmpSearch(mid, newPattern, nxt) != -1) {
						results[i] = nxt[nxt.length-1];
						break;
					}
					st = newPattern.toString();
					nxt = new int[st.length() + 1];
					nxt = getNext(st + "*", nxt);
				}
			}
		}
		for(int i=0; i<T; i++) {
			System.out.println(results[i]);
		}
	}
	
	public static int KmpSearch(String s, StringBuffer p, int[] next)
	{
		int i = 0;
		int j = 0;
		int sLen = s.length();
		int pLen = p.length();
		while (i < sLen && j < pLen)
		{
			if (j == -1 || s.charAt(i) == p.charAt(j))
			{
				i++;
				j++;
			}
			else
			{
				j = next[j];
			}
		}
		if (j == pLen)
			return i - j;
		else
			return -1;
	}

	
	public static int[] getNext(String p,int next[]) {
		int pLen = p.length();
		next[0] = -1;
		int k = -1;
		int j = 0;
		while (j < pLen - 1) {
			if (k == -1 || p.charAt(j) == p.charAt(k)) {
				++k;
				++j;
				next[j] = k;
			}
			else {
				k = next[k];
			}
		}
		return next;
	}

}
