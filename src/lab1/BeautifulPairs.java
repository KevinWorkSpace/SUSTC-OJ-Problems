package lab1;

import java.util.Scanner;

public class BeautifulPairs {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		in.nextLine();
		int[] arr = new int[T];
		for(int i=0; i<T; i++) {
			char[] c = {'#', 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'x', 'z'};
			int[][] a = new int[20][20];
			String str = in.nextLine();
			for(int j=0; j<str.length()-1; j++) {
				char c1 = str.charAt(j);
				char c2 = str.charAt(j+1);
				for(int k=0; k<c.length; k++) {
					if(c[k] == c1) {
						for(int m=0; m<c.length; m++) {
							if(c[m] == c2) {
								a[k][m]++;
								break;
							}
						}
					}
				}
			}
			//得到了a[20][20]矩阵的所有值
			int[] s = new int[20];
			int max = 0;
			while(true) {
				if(s[0] == 0) s[0]++;
				else {
					int p = 1;
					while(s[p] == 1) {
						s[p] = 0;
						p++;
					}
					s[p]++;
				}
				int sum = 0;
				for(int j=0; j<20; j++) {
					for(int k=0; k<20; k++) {
						sum += (s[j] - s[k])*(s[j] - s[k])*a[j][k];
					}
				}
				if(sum > max) max = sum;
				boolean flag = true;
				for(int j=0; j<s.length; j++) {
					if(s[j] == 0) flag = false;
				}
				if(flag) break;
			}
			arr[i] = max;
		}
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
}