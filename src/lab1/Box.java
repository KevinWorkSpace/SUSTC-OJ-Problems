package lab1;

import java.util.Scanner;

public class Box {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		boolean[] arr = new boolean[T];
		for(int i=0; i<T; i++) {
			int ta, tb, tc;
			ta = in.nextInt();
			tb = in.nextInt();
			tc = in.nextInt();
			int n = in.nextInt();
			int m = in.nextInt();
			if(ta == tb && tb == tc) {
				if(4*ta <= m && 3*ta <=n || 4*ta <= n && 3*ta <= m) {
					arr[i] = true;
				}
				else if(5*ta <= m && 3*ta <= n || 5*ta <= n && 3*ta <= m) {
					arr[i] = true;
				}
				else if(5*ta <= m && 2*ta <= n || 5*ta <= n && 2*ta <= m) {
					arr[i] = true;
				}
			}
			else {
				int[] as = {ta, ta, tb, tb, tc, tc};
				int[] bs = {tb, tc, ta, tc, ta, tb};
				int[] cs = {tc, tb, tc, ta, tb, ta};
				boolean flag = false;
				for(int j=0; j<as.length; j++) {
					if(judge(as[j], bs[j], cs[j], m, n)) {
						flag = true;
						break;
					}
				}
				if(flag) arr[i] = true;
			}	
		}
		for(int i=0; i<T; i++) {
			if(arr[i] == true) System.out.println("Yes");
			else System.out.println("No");
		}
	}
	
	public static boolean judge(int a, int b, int c, int m, int n) {
		if(2*b + a <= m && 2*c + 2*b <= n || 2*b + a <= n && 2*c + 2*b <= m) {
			return true;
		}
		else if(a + b + c <= m && 2*b + 2*c <= n || a + b + c <= n && 2*b + 2*c <= m) {
			return true;
		}
		else if(a + 2*b + c <= m && a + 2*c <= n || a + 2*b + c <= n && a + 2*c <= m) {
			return true;
		}
		else if(a + b + c <= m && a + 2*b + c <= n || a + b + c <= n && a + 2*b + c <= m) {
			return true;
		}
		else if(a + c <= m && a + 3*b + c <= n || a + c <= n && a + 3*b + c <= m) {
			return true;
		}
		else if(2*a + 2*c <= m && b + 2*c <= n || 2*a + 2*c <= n && b + 2*c <= m) {
			return true;
		}
		else return false;
	}
}
