package lab1;

import java.util.Scanner;

public class ChessGame {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		in.nextLine();
		boolean[] b = new boolean[T];   //true是Alice赢, false是bob赢
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			if(n == 1 && m == 1) {
				b[i] = false;
			}
			else b[i] = true;
		}
		for(int i=0; i<T; i++) {
			if(b[i] == true) System.out.println("Alice");
			else System.out.println("Bob");
		}
	}
}
