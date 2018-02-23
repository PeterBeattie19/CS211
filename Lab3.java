import java.util.*; 
import java.io.*; 


public  class Lab3 {

    public static void main(String[] args){
       Scanner in = new Scanner(System.in);
       
      // System.out.print("Enter your sentence: ");
       String sentence = in.nextLine();
       String binaryString="";      //this stores the string of binary code
       
       
       for(int i=0; i < sentence.length(); i++){        //go through the sentence
           int decimalValue = (int)sentence.charAt(i);      //convert to decimal
           String binaryValue = Integer.toBinaryString(decimalValue);      //convert to binary
           for(int j=7;j>binaryValue.length();j--){
               binaryString+="0";           //this loop adds in those pesky leading zeroes
            }
           binaryString += binaryValue+" "; //add to the string of binary
       }
       //System.out.println(binaryString);    //print out the binary
       
              
       int[] array = new int[256];      //an array to store all the frequencies
       
       for(int i=0; i < sentence.length(); i++){    //go through the sentence
           array[(int)sentence.charAt(i)]++;    //increment the appropriate frequencies
           
       }
       
       
       PriorityQueue < Tree >  PQ = new PriorityQueue < Tree >() ;  //make a priority queue to hold the forest of trees    
        
       
       for(int i=0; i<array.length; i++){ //go through frequency array
           if(array[i]>0){ //print out non-zero frequencies - cast to a char
                //System.out.println("'"+(char)i+"' appeared "+array[i]+((array[i] == 1) ? " time" : " times"));
     
               //FILL THIS IN:
               
               //MAKE THE FOREST OF TREES AND ADD THEM TO THE PQ
               
               //create a new Tree 
               //set the cumulative frequency of that Tree
               //insert the letter as the root node 
               //add the Tree into the PQ
               Tree temp = new Tree();
               temp.root = new Node();
               temp.root.letter = (char)i; 
               temp.frequency = array[i]; 
               temp.charValue = i; 
               PQ.add(temp); 
               
               
            }
        }
        
        
        while(PQ.size()>1){             //while there are two or more Trees left in the forest
            
            //FILL THIS IN:
            
            //IMPLEMENT THE HUFFMAN ALGORITHM
            
            //when you're making the new combined tree, don't forget to assign a default root node (or else you'll get a null pointer exception)
            //if you like, to check if everything is working so far, try printing out the letter of the roots of the two trees you're combining
            
            Tree one = PQ.poll(); 
            Tree two = PQ.poll(); 
            
            Tree t = new Tree();
            t.root = new Node(); 
            
            t.frequency = one.frequency + two.frequency; 
            t.root.leftChild = one.root;
            t.root.rightChild = two.root; 

            
            t.charValue = Math.min(one.charValue, two.charValue); 
            
            PQ.add(t); 
        }
        
        Tree HuffmanTree = PQ.poll();   //now there's only one tree left - get its codes
        
        
        //FILL THIS IN:
        
        //get all the codes for the letters and print them out
        //call the getCode() method on the HuffmanTree Tree object for each letter in the sentence

        //print out all the info
        
        for(int i = 0; i<sentence.length(); i++){
            System.out.print(HuffmanTree.getCode(sentence.charAt(i))); 
        }

    }      


   public static class Node{
   
   public char letter='\0';            //stores letter
   
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

}  // end class Node
    


public static class Tree implements Comparable<Tree>{
   public Node root; 
    public int charValue;// first node of tree
   public int frequency=0;
  ; 
// -------------------------------------------------------------
   public Tree(){   root = null; }            // no nodes in tree yet
// -------------------------------------------------------------

//the PriorityQueue needs to be able to somehow rank the objects in it
//thus, the objects in the PriorityQueue must implement an interface called Comparable
//the interface requires you to write a compareTo() method so here it is:

   public int compareTo(Tree object){ //
       if(frequency - object.frequency > 0)
           return 1;
       
       else if(frequency - object.frequency < 0)
           return -1;
       
       else{
           if(charValue > object.charValue)
               return 1;
           else if(charValue < object.charValue)
               return -1;
           else
               return 0; 
       }

   }
    
// -------------------------------------------------------------

   String path="error";     //this variable will track the path to the letter we're looking for 

   public String getCode(char letter){  //we want the code for this letter
 
       //FILL THIS IN:
       
       
       //How do you get the code for the letter? Maybe try a traversal of the tree
       //Track the path along the way and store the current path when you arrive at the right letter
       
        traverse(this.root, "", letter);     //return the path that results
       return path; 
            
   }
    
    public void traverse(Node root, String s, char l){
        if(root.leftChild == null && root.rightChild == null){
            if(root.letter == l){
                 this.path = s;
                return;
            }
            
        }
        else{
        
        traverse(root.leftChild, s+"0", l);
        traverse(root.rightChild, s+"1",l); 
        }
    }
    

}  // end class Tree
}
