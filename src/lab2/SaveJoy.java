package lab2;

import java.util.Arrays;
import java.util.Scanner;

public class SaveJoy {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] s = new int[n];
            for(int i=0; i<n; i++) {
                s[i] = in.nextInt();
            }
            int[] c = new int[n];
            for(int i=0; i<n; i++) {
                c[i] = in.nextInt();
            }
            double start = 1.0;
            double end = c[0];
            for(int i=0; i<n; i++) {
            	if(c[i] > end) end = c[i];
            }
            while(Math.abs(start-end)>=1E-6) {
                double middle = (start + end) / 2;
                double[] d = new double[n];
                for(int i=0; i<n; i++) {
                    d[i] = c[i]*s[i] - s[i]*middle;
                }
                Arrays.sort(d);
                double sum = 0;
                for(int i=n-1; i>=k; i--) {
                	sum += d[i];
                }
                for(int i=k-1; i>=0; i--) {
                	if(d[i] > 0) sum += d[i]; 
                } 
                if(sum > 0) {
                    start = middle;
                }
                else {
                    end = middle;
                }
            }
            System.out.println(String.format("%.3f", start));
        }
    }
}
