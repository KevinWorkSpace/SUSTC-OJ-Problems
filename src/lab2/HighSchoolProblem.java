package lab2;

import java.math.BigDecimal;
import java.util.Scanner;

public class HighSchoolProblem {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		double[] results = new double[T];
		for(int i=0; i<T; i++) {
			int Y = in.nextInt();
			double start = 0;
			double end = 100;
			double index = 0;
			int count = 0;
			while(count <= 100) {
				double middle = (start + end) / 2;
				BigDecimal bd = BigDecimal.valueOf(middle);
				String str;
				
				str = bd.pow(6).stripTrailingZeros().toPlainString();
				BigDecimal bd1 = new BigDecimal(str);
				bd1 = bd1.multiply(BigDecimal.valueOf(35));
				
				str = bd.pow(5).stripTrailingZeros().toPlainString();
				BigDecimal bd2 = new BigDecimal(str);
				bd2 = bd2.multiply(BigDecimal.valueOf(36));
				
				str = bd.pow(2).stripTrailingZeros().toPlainString();
				BigDecimal bd3 = new BigDecimal(str);
				bd3 = bd3.multiply(BigDecimal.valueOf(9));
				
				BigDecimal bd4 = bd.multiply(BigDecimal.valueOf(8));
				
				BigDecimal bd5 = BigDecimal.valueOf(-2*Y);
				
				bd = bd1.add(bd2);
				bd = bd.add(bd3);
				bd = bd.add(bd4);
				bd = bd.add(bd5);
				if(bd.compareTo(BigDecimal.valueOf(0)) == 0) {
					index = middle;
					break;
				}
				else if(bd.compareTo(BigDecimal.valueOf(0)) == 1) {
					end = middle;
				}
				else {
					start = middle;
				}
				count++;
				if(count == 101) {
					index = middle;
				}
			}
			results[i] = 5*Math.pow(index, 7) + 6*Math.pow(index, 6) + 3*Math.pow(index, 3) + 4*Math.pow(index, 2) - 2*index*Y;
			
		}
		for(int i=0; i<T; i++) {
			System.out.println("Case " + (i+1) + ": " + String.format("%.4f", results[i]));
		}
	}
}
