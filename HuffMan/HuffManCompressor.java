import java.util.*; 
import java.io.*; 

public class HuffManCompressor {
	public static void main (String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

		String s = "The cat sat on the mat"; 


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
				q.add(freq[i], (char)i); 
		}


		/*for(int i = 0; i<count; i++){
			Node pop = q.remove(); 
			System.out.println(pop.c +" "+ pop.data); 
		}*/


		//Apply compression 
		Node root = new Node(); 
		while(q.size() > 1){

			//Pop off the two nodes with the smallest frequencies 
			Node one = q.remove(); 
			Node two = q.remove(); 

			//Create a new Node with its data set to the sum of the data values from nodes one and two, char value doesn't matter 
			Node newNode = new Node((one.data + two.data), '#');  

			newNode.left = one;
			newNode.right = two;
			root = newNode;



			q.add(newNode); 
		}

		printCodes(root, ""); 
	}

	public static void printCodes(Node root, String code){
		if(root.left==null && root.right == null){
			System.out.println("The code for " +root.c+ " is " +code); 
		}

		else{
			printCodes(root.left, code+"0");
			printCodes(root.right, code+"1"); 
		}
	}
} 