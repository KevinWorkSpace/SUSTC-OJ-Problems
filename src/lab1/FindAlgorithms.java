package lab1;

import java.util.Scanner;

public class FindAlgorithms {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();   
		in.nextLine();
		boolean[] b = new boolean[T];
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			in.nextLine();
			String[] algSet = new String[n];
			for(int j=0; j<n; j++) {
				String str = in.nextLine();
				algSet[j] = str.toLowerCase();
			}
			int m = in.nextInt();
			in.nextLine();
			String describe = in.nextLine();
			describe = describe.toLowerCase();
			String[] strSet = describe.split("\\s+");
			boolean exist = false;
			for(int j=0; j<strSet.length; j++) {
				for(int k=0; k<algSet.length; k++) {
					if(algSet[k].equals(strSet[j])) {
						exist = true;
						break;
					}
				}
				if(exist == true) {
					break;
				}
			}
			if(exist == true) {
				b[i] = true;
			}
			else {
				b[i] = false;
			}
		}
		for(int i=0; i<b.length; i++) {
			if(b[i] == true) System.out.println("Appeared");
			else System.out.println("Not appeared");
		}
	}
}
