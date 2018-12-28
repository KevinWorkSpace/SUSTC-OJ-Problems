package lab3;

import java.util.Scanner;

public class CombinePolynomials {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		LinkedList[] ll = new LinkedList[T];
		for(int i=0; i<T; i++) {
			LinkedList l = new LinkedList();
			int n1 = in.nextInt();
			int cof = 0;
			int exp = 0;
			for(int j=0; j<n1; j++) {
				cof = in.nextInt();
				exp = in.nextInt();
				l.add(cof, exp);
				
			}
			int n2 = in.nextInt();
			for(int j=0; j<n2; j++) {
				cof = in.nextInt();
				exp = in.nextInt();
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
		
		public void add(int cof, int exp) {
			Node n = new Node(cof, exp);
			if(head == null) {
				head = n;
			}
			else {
				Node tmp = head;
				while (tmp.next != null) {
		            tmp = tmp.next;
		        }
		        tmp.next = n;
			}
		}
		
		public void insert(int cof, int exp) {
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
