package lab3;

import java.util.Scanner;

public class DifferentiateThePolynomials {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		LinkedList[] ll = new LinkedList[T];
		for(int i=0; i<T; i++) {
			LinkedList l = new LinkedList();
			int n1 = in.nextInt();
			int cof = 0;
			int exp = 0;
			int c = 0;
			int e = 0;
			for(int j=0; j<n1; j++) {
				c = in.nextInt();
				e = in.nextInt();
				cof = c * e;
				exp = e - 1;
				l.insert(cof, exp);
			}
			ll[i] = l;
		}
		for(int i=0; i<T; i++) {
			ll[i].print();
			if(i != T-1) System.out.println();
		}
	}
	
	static class LinkedList {
		
		Node head;
		
		public LinkedList() {
			head = null;
		}
		
		public void insert(int cof, int exp) {
			if(this.head == null) {
				this.head = new Node(cof, exp);
			}
			else {
				Node node = this.head;
				Node newNode = new Node(cof, exp);
				if(head.exp > exp) {
					newNode.next = head;
					head = newNode;
				}
				else if(head.exp == exp) {
					head.cof += cof;
				}
				else {
					while(node.next != null && newNode.exp > node.next.exp) {
						node = node.next;
					}
					
					if(node.next != null) {
						if(newNode.exp == node.next.exp) {
							node.next.cof += cof;
						}
						else {
							newNode.next = node.next;
							node.next = newNode;
						}
					}
					else {
						newNode.next = node.next;
						node.next = newNode;
					}
				}
			}
		}
		
		public void print() {
			Node node = this.head;
			boolean flag = false;
			while(node != null) {
				if(node.cof != 0) {
					flag = true;
					if(node.cof != 1 && node.cof != -1 || 
					   node.cof == -1 && node.exp == 0 ||
					   node.cof == 1 && node.exp == 0) {
						System.out.print(node.cof);
					}
					if(node.cof == -1 && node.exp != 0) {
						System.out.print("-");
					}
					if(node.exp != 0 && node.exp != 1) {
						System.out.print("x^" + node.exp);
					}
					if(node.exp == 1) {
						System.out.print("x");
					}
					if(node.next != null) {
						if(node.next.cof > 0) {
							System.out.print("+");
						}
					}
				}
				else {
					if(node.next != null) {
						if(node.next.cof > 0 && flag) {
							System.out.print("+");
						}
					}
				}
				node = node.next;
			}
			if(!flag) {
				System.out.print(0);
			}
		}
		
		private static class Node {
			int cof;
			int exp;
			Node next;
			
			Node(int cof, int exp) {
				this.cof = cof;
				this.exp = exp;
			}
		}
	}
}
