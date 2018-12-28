package lab1;

import java.math.BigDecimal;
import java.util.Scanner;

public class TowerOfHanoi {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
	    int[] a = new int[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			BigDecimal bd = BigDecimal.valueOf(3);
			String str;
			str = bd.pow(n).stripTrailingZeros().toPlainString();
			bd = new BigDecimal(str);
			BigDecimal bd3 = BigDecimal.valueOf(-1);
			bd = bd.add(bd3);
			BigDecimal bd2 = BigDecimal.valueOf(1000000007);
			BigDecimal[] results = bd.divideAndRemainder(bd2);
			a[i] = results[1].intValue();
		}
		for(int i=0; i<T; i++) {
			System.out.println(a[i]);
		}
	}
	
//	public static void hanoi(int n, String a, String b, String c) {
//		if(n == 1) {
//			if(a.equals("a") && c.equals("c") || a.equals("c") && c.equals("a")) {
////				System.out.println(a + "--->" + b);
////				System.out.println(b + "--->" + c);
//				s = (s + 2) % 1000000007;
//			}
//			else {
////				System.out.println(a + "--->" + c);
//				s = (s + 1) % 1000000007;
//			}
//		}
//		else {
//			hanoi(n-1, a, b, c);
////			System.out.println(a + "--->" + b);
//			s = (s + 1) % 1000000007;
//			hanoi(n-1, c, b, a);
////			System.out.println(b + "--->" + c);
//			s = (s + 1) % 1000000007;
//			hanoi(n-1, a, b, c);
//		}
//	}
}
