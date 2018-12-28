package lab1;

import java.util.Scanner;

public class MaximumDifference {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int[] arr = new int[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int[] a = new int[n];
			for(int j=0; j<n; j++) {
				a[j] = in.nextInt();
			}
			//数组构建完成
			int max = a[0];
			int maxD = a[0] - a[1];
			for(int j=1; j<a.length; j++) {
				if(max - a[j] > maxD) maxD = max - a[j];
				if(a[j] > max) max = a[j];
			}
			arr[i] = maxD;
		}
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
