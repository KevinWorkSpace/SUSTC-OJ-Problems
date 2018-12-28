package lab3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class HuaXinBookStore {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		while(n != 0) {
			int count = 0;
			String str = in.next();
			LinkedList<Character> list = new LinkedList<Character>();
			ArrayList<Character> noBookList = new ArrayList<Character>();
			for(int i=0; i<str.length(); i++) {
				char c = str.charAt(i);
				if(!list.contains(c) && list.size() < n) {
					if(!noBookList.contains(c)) {
						list.add(c);
					}
				}
				else if(!list.contains(c) && list.size() >= n) {
					if(!noBookList.contains(c)) {
						noBookList.add(c);
						count++;
					}
				}
				else {
					Iterator<Character> it = list.iterator();
					int cnt = 0;
					while(it.hasNext()) {
						char ch = it.next();
						if(ch != c) {
							cnt++;
						}
						else {
							break;
						}
					}
					list.remove(cnt);
				}
			}
			arr.add(count);
			n = in.nextInt();
		}
		Iterator<Integer> it = arr.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
