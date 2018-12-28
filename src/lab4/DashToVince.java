package lab4;

import java.util.Scanner;

public class DashToVince {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int[] result = new int[T];
		int[][] a = new int[24][4];
		//0上, 1下, 2左, 3右
		int cnt = 0;
		while(cnt<24) {
			//0表示的
			for(int i=0; i<4; i++) {
				//1表示的
				boolean f = true;
				for(int j=0; j<4; j++) {
					//2表示的
					if(j != i) {
						boolean fl = true;
						for(int k=0; k<4; k++) {
							//3表示的
							if(k != i && k!= j) {
								boolean flag = true;
								for(int m=0; m<4; m++) {
									if(m != i && m != j && m != k) {
										a[cnt][0] = i;
										a[cnt][1] = j;
										a[cnt][2] = k;
										a[cnt++][3] = m;
										if(cnt == 24) {
											flag = false;
											break;
										}
									}
								}
								if(!flag) {
									fl = false;
									break;
								}
							}
						}
						if(!fl) {
							f = false;
							break;
						}
					}
				}
				if(!f) {
					break;
				}
			}
		}
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			in.nextLine();
			int sx = 0;
			int sy = 0;
			String[][] str = new String[n][m];
			for(int j=0; j<n; j++) {
				String s = in.next();
				for(int k=0; k<s.length(); k++) {
					String st = String.valueOf(s.charAt(k));
					str[j][k] = st;
					if(str[j][k].equals("S")) {
						sx = j;
						sy = k;
					}
				}
			}
			in.nextLine();
			String sequence = in.nextLine();
			int count = 0;
			for(int j=0; j<24; j++) {
				int[] strategy = a[j];
				boolean flag = false;
				int currentX = sx;
				int currentY = sy;
				for(int k=0; k<sequence.length(); k++) {
					char cc = sequence.charAt(k);
					String sr = String.valueOf(cc);
					int c = Integer.parseInt(sr);
					if(strategy[c] == 0) {
						//向上移动
						currentX--;
						if(currentX == -1) {
							break;
						}
						else if(str[currentX][currentY].equals("#")) {
							break;
						}
						else if(str[currentX][currentY].equals("E")) {
							flag = true;
							break;
						}
					}
					else if(strategy[c] == 1) {
						//向下移动
						currentX++;
						if(currentX == n) {
							break;
						}
						else if(str[currentX][currentY].equals("#")) {
							break;
						}
						else if(str[currentX][currentY].equals("E")) {
							flag = true;
							break;
						}
					}
					else if(strategy[c] == 2) {
						//向左移动
						currentY--;
						if(currentY == -1) {
							break;
						}
						else if(str[currentX][currentY].equals("#")) {
							break;
						}
						else if(str[currentX][currentY].equals("E")) {
							flag = true;
							break;
						}
					}
					else if(strategy[c] == 3) {
						//向右移动
						currentY++;
						if(currentY == m) {
							break;
						}
						else if(str[currentX][currentY].equals("#")) {
							break;
						}
						else if(str[currentX][currentY].equals("E")) {
							flag = true;
							break;
						}
					}
				}
				if(flag) {
					count++;
				}
			}
			result[i] = count;
		}
		for(int i=0; i<T; i++) {
			System.out.println(result[i]);
		}
	}
}
