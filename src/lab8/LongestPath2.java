package lab8;

import java.util.ArrayList;
import java.util.Scanner;

public class LongestPath2 {
	
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int i=0; i<T; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			dp = new int[n];
			ArrayList<Node>[] a = new ArrayList[n];
			for(int j=0; j<n; j++) {
				a[j] = new ArrayList<Node>();
			}
			boolean[] sourses = new boolean[n];
			for(int j=0; j<m; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				int z = in.nextInt();
				Node node = new Node(y, z);
				a[x-1].add(node);
				sourses[y-1] = true;
			}
			int longestPath = 0;
			for(int j=0; j<n; j++) {
				if(!sourses[j]) {
					int temp = DP(j, a);
					if(temp > longestPath) {
						longestPath = temp;
					}
				}
			}
			System.out.println(longestPath);
		}
	}
	
	static class Node {
		int val;
		int weight;
		
		public Node(int val, int weight) {
			this.val = val;
			this.weight = weight;
		}
	}
	
	public static int DP(int i, ArrayList<Node>[] a){
		if(dp[i] > 0) return dp[i];
		int size = a[i].size();
		for(int j=0; j<size; j++){  
			int temp= DP(a[i].get(j).val-1, a) + a[i].get(j).weight; 
			if(dp[i] < temp){
				dp[i] = temp; 
			}
		}
		return dp[i]; 
	}
}
