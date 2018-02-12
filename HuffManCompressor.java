import java.util.*; 
import java.io.*; 

public class HuffManCompressor {
	public static void main (String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

		String s = "hello, this is the text to be compressed!"; 


		int freq[] = new int[256]; 
		int count = 0;  //How many unique characters 

		for(int i = 0; i<s.length(); i++){
			if(freq[(int)s.charAt(i)] == 0)
				count++; 

			freq[(int)s.charAt(i)]++; 
		}

		MinHeap q = new MinHeap(count); 

		for(int i = 0; i<256; i++){
			if(freq[i] != 0)
				q.insert(freq[i], (char)i); 
		}


		for(int i = 0; i<count; i++){
			Node pop = q.remove(); 
			System.out.println(pop.c +" "+ pop.data); 
		}



	}
} 