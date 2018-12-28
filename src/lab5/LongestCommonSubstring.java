package lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LongestCommonSubstring {
	
	public static void main(String[] args) {
		InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
		int T = in.nextInt();
		String[] results = new String[T];
		for(int i=0; i<T; i++) {
			int N = in.nextInt();
			String[] texts = new String[N];
			for(int j=0; j<N; j++) {
				texts[j] = in.next();
			}
			int minLength = texts[0].length();
			int minIndex = 0;
			for(int j=1; j<N; j++) {
				int l = texts[j].length();
				if(l < minLength) {
					minLength = l;
					minIndex = j;
				}
			}
			String pText = texts[minIndex];
			StringBuffer tempCom = new StringBuffer("");
			String commonStr = "";
			char [] smallStrChars = pText.toCharArray();               
			for (char c: smallStrChars){
		        tempCom.append(c);
		        for (String s : texts){
		            if(!s.contains(tempCom)){
		            	boolean flag = false;
		            	while(!flag && tempCom.length() > 0) {
		            		flag = true;
		            		tempCom.deleteCharAt(0);
		            		for(String str : texts) {
		            			if(!str.contains(tempCom)) {
		            				flag = false;
		            			}
		            		}
		            	}
		                break;
		            }               
		        }
		        if(tempCom.length() != 0 && tempCom.length() > commonStr.length()){
		            commonStr = tempCom.toString();  
		        }
		    }
			String[] possibleAnswers = new String[pText.length()]; 
			int num = 0;
			tempCom = new StringBuffer("");
			for (char c: smallStrChars){
		        tempCom.append(c);
		        for (String s : texts){
		            if(!s.contains(tempCom)){
		            	boolean flag = false;
		            	while(!flag && tempCom.length() > 0) {
		            		flag = true;
		            		tempCom.deleteCharAt(0);
		            		for(String str : texts) {
		            			if(!str.contains(tempCom)) {
		            				flag = false;
		            			}
		            		}
		            	}
		                break;
		            }               
		        }
		        if(tempCom.length() == commonStr.length()){
		        	possibleAnswers[num++] = tempCom.toString();
		        }
		    }
		    String[] RealPossAnswers = new String[num];
		    for(int j=0; j<num; j++) {
		    	RealPossAnswers[j] = possibleAnswers[j];
		    }
		    Arrays.sort(RealPossAnswers);
		    results[i] = RealPossAnswers[0];
		}
		for(int i=0; i<results.length; i++) {
			if(results[i] != "") {
				System.out.println(results[i]);
			}
			else {
				System.out.println("Hong");
			}
		}
	}
	
	static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
