package lab5;

import java.util.Scanner;

public class SkrSkr {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int[] results = new int[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			in.nextLine();
			char[] rhymes = new char[N];
			for(int j=0; j<N; j++) {
				String str = in.nextLine();
				char c = str.charAt(str.length()-1);
				rhymes[j] = c;
			}
			int maxCount = 1;
			char firstRhyme = rhymes[0];
			int count = 1;
			for(int j=1; j<rhymes.length; j++) {
				if(rhymes[j] == firstRhyme) {
					count++;
					if(count > maxCount) {
						maxCount = count;
					}
				}
				else {
					firstRhyme = rhymes[j];
					count = 1;
				}
			}
			results[i] = maxCount;
		}
		for(int i=0; i<results.length; i++) {
			System.out.println(results[i]);
		}
	}
}
