package lab3;

import java.util.Scanner;

public class JoysNewJob {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		long[] result = new long[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int k = in.nextInt();
			int[] c = new int[n];
			int[] w = new int[n+1];
			for(int j=0; j<n; j++) {
				c[j] = in.nextInt(); //比如c[j] = 5
				w[c[j]] = j;  //表示值为5的节点是第j个
			}
			//数组构造完毕
			long sum = 0;
			for(int j=1; j<w.length; j++) {
				int leftCount = 0;
				int rightCount = 0;
				int leftPoint = -1;
				int rightPoint = c.length;
				long gx = 0;
				int[] lp = new int[k-1];
				int lc = 0;
				int[] rp = new int[k-1];
				int rc = 0;
				for(int m=w[j]-1; m>=0; m--) {
					if(c[m] > c[w[j]] && leftCount < k-1) {
						leftCount ++;
						lp[lc++] = m;
					}
					else if(c[m] > c[w[j]] && leftCount == k-1) {
						leftPoint = m;
						break;
					}
				}
				//左边比x大的lc个点的位置已经存在lp中
				for(int m=w[j]+1; m<c.length; m++) {
					if(c[m] > c[w[j]] && rightCount < k-1) {
						rightCount ++;
						rp[rc++] = m;
					}
					else if(c[m] > c[w[j]] && rightCount == k-1) {
						rightPoint = m;
						break;
					}
				}
				//右边比x大的rc个点的位置已经存在rp中
				for(int m=0; m<=lc; m++) {
					//取左边m个比x大的值
					int lCond = 0;
					int rCond = 0;
					if(k-1-m <= rc) {
						//若右边比x大的值数量足够, 取到能取到的最右边的位置
						int rIndex = k-1-m-1;
						//计算与后一个位置之间的距离, 并把距离加一即为左边的情况种类
						if(m <= lc-1 && m > 0) {
							lCond = lp[m-1] - lp[m];
						}
						else if(m == 0) {
							if(lc == 0) {
								lCond = w[j] - leftPoint;
							}
							else {
								lCond = w[j] - lp[0];
							}
						}
						else if(m == lc){
							lCond = lp[m-1] - leftPoint;
						}
						if(rIndex <= rc-2 && rIndex >= 0) {
							rCond = rp[rIndex + 1] - rp[rIndex];
						}
						else if(rIndex == -1) {
							if(rc == 0) {
								rCond = rightPoint - w[j];
							}
							else {
								rCond = rp[0] - w[j];
							}
						}
						else {
							rCond = rightPoint - rp[rIndex];
						}
					}
					gx += rCond * lCond;
				}
				sum += j * gx;
			}
			result[i] = sum;
		}
		for(int i=0; i<T; i++) {
			System.out.println(result[i]);
		}
	}
}
