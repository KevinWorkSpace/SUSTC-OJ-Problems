package lab3;

import java.util.LinkedList;
import java.util.Scanner;


public class ThreeBody {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		boolean[] b = new boolean[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int e = in.nextInt();
			LinkedList<Integer> list = new LinkedList<Integer>();
			for(int j=0; j<n; j++) { 
				list.add(j); 
			} 
			int point=0;
			int number=1; 
			boolean flag = true;
			while(list.size()>1) { 
				if(number % (m+1)==0) { 
					if(list.get(point) == e) {
						b[i] = false;
						flag = false;
						break;
					}
					list.remove(point); 
				} 
				else {
					point ++;
				}
				if(point == list.size()) { 
					point=0; 
				} 
				number ++;
			}
			if(flag) b[i] = true;
		}
		for(int i=0; i<T; i++) {
			if(b[i] == true) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
 	}
	

}
