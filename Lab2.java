import java.util.*; 
import java.io.*; 

public class Lab2{
	public static void main (String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

		System.out.print("Enter your sentence: ");
		String sentence = br.readLine(); 

		int freq[] = new int[256]; 

		for(int i = 0; i<sentence.length(); i++){
			
			String temp = Integer.toBinaryString((int)sentence.charAt(i)); 
			if(temp.length() == 5)
				temp = "00" + temp; 
			
			if(temp.length() == 6)
				temp = "0" + temp;
			
			System.out.print(temp +" "); 
			freq[(int)sentence.charAt(i)]++; 

			if(i%6 == 0 && i!=0)
				System.out.println(); 
		}

		System.out.println();

		for(int i = 0; i<256; i++){
			if(freq[i] != 0 && freq[i]>1)
				System.out.println("'"+(char)i +"'" +" appeared "+ freq[i] +" times"); 
			else if(freq[i] != 0)
				System.out.println("'"+ (char)i +"'" +" appeared "+ freq[i] +" time");
		}

	}
} 
