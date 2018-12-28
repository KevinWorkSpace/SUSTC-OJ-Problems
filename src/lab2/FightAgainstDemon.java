package lab2;

import java.util.Scanner;

public class FightAgainstDemon {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int[] arr = new int[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[] swordLights = new int[n];
			for(int j=0; j<n; j++) {
				swordLights[j] = in.nextInt();
			}
			int count = 0;
			for(int j=0; j<n; j++) {
				int a = swordLights[j];
				for(int k=0; k<n; k++) {
					int b = swordLights[k];
					if(a + b == m) count++;
				}
			}
			count = count / 2;
			arr[i] = count;
		}
		for(int i=0; i<T; i++) {
			System.out.println(arr[i]);
		}
	}
}
