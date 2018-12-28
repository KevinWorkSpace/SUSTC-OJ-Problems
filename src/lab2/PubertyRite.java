package lab2;

import java.util.Scanner;

public class PubertyRite {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		double[] d = new double[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			double[] x = new double[N];
			double[] w = new double[N];
			for(int j=0; j<N; j++) {
				x[j] = in.nextDouble();
				w[j] = in.nextDouble();
			}
			double eps = 1e-12;
			double start = x[0];
			double end = x[N-1];
			double distance = 0;
			while(start + eps <= end) {
				double mid = (start + end) / 2;
				double midmid = (mid + end) / 2;
				double vm = 0;
				for(int j=0; j<N; j++) {
					vm += Math.pow(Math.abs(x[j]-mid), 3) * w[j];
				}
				double vmm = 0;
				for(int j=0; j<N; j++) {
					vmm += Math.pow(Math.abs(x[j]-midmid), 3) * w[j];
				}
				if(vm <= vmm) {
					end = midmid;
					if(start + eps > end) {
						distance = vm;
					}
				}
				else {
					start = mid;
					if(start + eps > end) {
						distance = vmm;
					}
				}
			}
			d[i] = distance;
		}
		for(int i=0; i<T; i++) {
			System.out.println("Case #" + (i+1) + ": " + String.format("%.0f", d[i]));
		}
	}
}
