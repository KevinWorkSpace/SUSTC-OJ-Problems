package lab1;

import java.util.Scanner;
//9 7 1不对
public class ThreeDPrint {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int[] as = new int[T];
		int[] bs = new int[T];
		int[] cs = new int[T];
		for(int i=0; i<T; i++) {
			as[i] = in.nextInt();
			bs[i] = in.nextInt();
			cs[i] = in.nextInt();
		}
		for(int i=0; i<T; i++) {
			int a = as[i];
			int b = bs[i];
			int c = cs[i];
			//开始打印上半部分
			int s = 0;
			int p = 0;
			for(int j=0; j<2*b; j++) {
				for(int k=2*b-j; k>0; k--) {
					//打印左上角的点
					System.out.print(".");
				}
				if(j % 2 == 0) {
					//偶数, 打印边上的+-和侧面
					for(int m = 0; m<a; m++) {
						System.out.print("+-");
					}
					System.out.print("+");
					for(int m=0; m<j/2 && j / 2 <= c; m++) {
						System.out.print(".+");
						p = m;
					}
					if(j/2 > c) {
						for(int q=0; q<p+1; q++) {
							System.out.print(".+");
						}
						for(int q=0; q<j-2*c; q++) {
							System.out.print(".");
						}
					}
				}
				else {
					//奇数, 打印/和侧面
					for(int m=0; m<a; m++) {
						System.out.print("/.");
					}
					System.out.print("/|");
					for(int m=0; m<(j-1)/2 && (j-1) / 2 < c; m++) {
						System.out.print("/|");
						s = m;
					}
					
					//下面有问题
					if((j-1)/2 >= c) {
						System.out.print("/");
						if(c != 1) {
							for(int q=0; q<s+1; q++) {
								System.out.print("|/");
							}
						}
						else {
							for(int q=0; q<s; q++) {
								System.out.print("|/");
							}
						}
						for(int q=0; q<j-2*c; q++) {
							System.out.print(".");
						}
					}
				}
				System.out.println();
			}
			
			
//			//开始打印正面部分
			for(int j=0; j<2*c; j++) {
				if(j % 2 == 0) {
					//偶数, 打印+-
					for(int k=0; k<a; k++) {
						System.out.print("+-");
					}
					System.out.print("+");
					if(j <= 2*(c-b)) {            
						for(int k=0; k<b; k++) {
							System.out.print(".+");
						}
					}
					else {
						for(int k=0; k<(2*c - j) / 2; k++) {
							System.out.print(".+");
						}
						for(int k=0; k<2*b - 2*c + j; k++) {  
							System.out.print(".");
						}
					}
				}
				else {
					//奇数, 打印|.
					for(int k=0; k<a; k++) {
						System.out.print("|.");
					}
					System.out.print("|");
					if((j+1) / 2  <= c-b) {                
						for(int k=0; k<b; k++) {
							System.out.print("/|");
						}
					}
					else {
						System.out.print("/");
						for(int k=0; k<(2*c-1 -j) / 2; k++) {
							System.out.print("|/");
						}
						for(int k=0; k<2*b - 2*c + j; k++) {    
							System.out.print(".");
						}
					}
				}
				System.out.println();
			}
			for(int j=0; j<a; j++) {
				System.out.print("+-");
			}
			System.out.print("+");
			for(int j=0; j<2*b; j++) {
				System.out.print(".");
			}
			if(i != T-1) System.out.println();
		}
	}
}
 