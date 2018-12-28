package lab2;

import java.util.Arrays;
import java.util.Scanner;

public class SportMeeting {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int L = in.nextInt();
			int n = in.nextInt();
			int m = in.nextInt();
			int[] d = new int[n+2];
			d[0] = 0;
			for(int i=1; i<n+1; i++) {
				d[i] = in.nextInt();
			}
			d[n+1] = L;
			Arrays.sort(d);
			int start = d[1] - d[0];
			for(int i=0; i<n; i++) {
				if(d[i+1] - d[i] < start) start = d[i+1] - d[i]; 
			}
			int end = d[n+1];
			int mid = 0;
			while(start < end) {
				mid = (start + end) / 2;
				int count = 0;
				int num = 0;
				boolean flag = false;
				while(num < d.length) {
					for(int i=num+1; i<d.length; i++) {
						if(d[i] - d[num] > mid) {
							if(i-num == 1) {
								count = m;
								flag = true;
								break;
							}
							num = i-1;
							count ++;
							break;
						}
						if(i == d.length-1) {
							flag = true;
							break;
						}
					}
					if(count > m-1) break;
					if(flag) break;
				}
				if(count > m-1) {
					start = mid+1;
				}
				else {
					end = mid;
				}
			}
			System.out.println(start);
		}
	}
}
