package lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class CasinoFinanceWork {
	
	public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		ArrayList<Integer>[] arr = (ArrayList<Integer>[])new ArrayList[T];
		for(int i=0; i<T; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			LinkedList<Node> list = new LinkedList<Node>();
			for(int j=0; j<n; j++) {
				int a = in.nextInt();
				Node newNode = new Node(j, a);
				list.add(newNode);
			}
			LinkedList<Node> l2 = new LinkedList<Node>();
			l2.addAll(list);
			Collections.sort(list);
			Node midNode = list.get((n-1) / 2);
			arr[i].add(midNode.ai);
			int midIndex = (n-1) / 2;
			int point = n - 1;
			while(l2.size() > 1) {
				int k = midNode.i;
				Node n1 = l2.get(point--);
//				list.remove(n1);
				l2.remove(n1);
				Node n2 = l2.get(point--);
//				list.remove(n2);
				l2.remove(n2);
				int i1 = n1.i;
				int i2 = n2.i;
				if(i1 <= k && i2 <= k) {
					midNode = list.get(++midIndex);
					arr[i].add(list.get(midNode.i).ai);
				}
				else if(i1 >= k && i2 >= k) {
					midNode = list.get(--midIndex);
					arr[i].add(midNode.ai);
				}
				else if(i1 >= k && i2 <= k || i1 <= k && i2 >= k) {
					arr[i].add(midNode.ai);
				}
			}
		}
		for(int i=0; i<T; i++) {
			int n = arr[i].size();
			for(int j=0; j<n; j++) {
				System.out.print(arr[i].get(n-1-j) + " ");
			}
			System.out.println();
		}
	}
	
	static class Node implements Comparable<Node> {
		int i;
		int ai;
		
		public Node(int i, int ai) {
			this.i = i;
			this.ai = ai;
		}

		@Override
		public int compareTo(Node o) {
			return this.ai - o.ai;
		}
	}
}
