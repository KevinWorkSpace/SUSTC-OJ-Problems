package lab5;

import java.util.Scanner;

public class MatchingProblem {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		boolean[] results = new boolean[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			in.nextLine();
			String s1 = in.nextLine();
			String s2 = in.nextLine();
			boolean hasXing = false;
			for(int l=0; l<s1.length(); l++) {
				if(s1.charAt(l) == '*') {
					hasXing = true;
					break;
				}
			}
			int p = 0;
			int q = 0;
			boolean flag = true;
			while(p < s1.length() && s1.charAt(p) != '*' && q < s2.length()) {
				if(s1.charAt(p) == s2.charAt(q)) {
					p++;
					q++;
				}
				else {
					flag = false;
					break;
				}
			}
			if(flag) {
				if(p == s1.length() && q <= s2.length()) {
					flag = true;
				}
				else if(q == s2.length() && p == s1.length()-1 && s1.charAt(p) == '*') {
					flag = true;
				}
				else if(p <= s1.length() && q <= s2.length()) {
					flag = true;
				}
				else {
					flag = false;
				}
			}
			int k = n - 1;
			int j = m - 1;
			if(flag) {
				while(k >= 0 && s1.charAt(k) != '*' && j >= 0) {
					if(s1.charAt(k) == s2.charAt(j)) {
						k--;
						j--;
					}
					else {
						flag = false;
						break;
					}
				}
			}
			if(flag) {
				if(k < 0 && j >= -1) {
					flag = true;
				}
				else if(j < 0 && k == 0 && s1.charAt(k) == '*') {
					flag = true;
				}
				else if(k >= 0 && j >= 0) {
					flag = true;
				}
				else {
					flag = false;
				}
			}
			if(flag) {
				if(p<=k && hasXing) {
					results[i] = true;
				}
				else {
					if(hasXing) {
						results[i] = false;
					}
					else {
						results[i] = true;
					}
				}
			}
			else {
				results[i] = false;
			}
		}
		for(int i=0; i<T; i++) {
			if(results[i]) System.out.println("YES");
			else System.out.println("NO");
		}
	}
}
