package lab6;

import java.util.Random;

public class Ghelper {
	
	public static void main(String[] args) {
		System.out.println(1);
		Random r = new Random();
		int N = r.nextInt(5) + 5;
		System.out.println(N);
		int[] b = new int[N+1];
		int num = 0;
		while(num < N) {
			int t = r.nextInt(N) + 1;
			if(b[t] == 0) {
				b[t] = 1;
				num++;
				System.out.print(t + " ");
			}
		}
		for(int i=0; i<N-1; i++) {
			int left = r.nextInt(N)+1;
			int right = r.nextInt(N)+1;
			while(left == right) {
				right = r.nextInt(N) + 1;
			}
			System.out.println(left + " " + right);
		}
	}
}
