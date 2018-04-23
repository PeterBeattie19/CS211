import java.util.*;
import java.io.*; 

public class Lab8 {

    public static void main(String[] args) throws IOException{


        /*File f = new File("dictionary.txt"); 
        int items = 216557; 
        BufferedReader br = new BufferedReader(new FileReader(f)); 
        String[] contents = new String[items];


        String line = br.readLine();
        int j = 0; 

        while(line!=null){
            contents[j] = line; 
            line = br.readLine(); 
            j++; 
        } */ 


        Scanner myscanner = new Scanner(System.in);
        
        int items = myscanner.nextInt();
        myscanner.nextLine();
        
        String[] contents = new String[items];

        for (int i = 0; i < items; i++) {
            contents[i] = myscanner.nextLine();
        } 



        HashTable mytable = new HashTable();
        Solution mysolution = new Solution(mytable);

        mysolution.fill(contents);
        mysolution = new Solution(mytable);

        for (int i = 0; i < items; i++) {
            int rand = (int)(Math.random() * items);
            String temp = contents[i];
            contents[i] = contents[rand];
            contents[rand] = temp;
        }

        for (int i = 0; i < items; i++) {
            int slot = mysolution.find(contents[i]);
            if (!mytable.check(slot, contents[i])) {
               // System.out.println("error!");
            }
        }

        System.out.println(mytable.gettotal());

    }
}


class HashTable {

    public int size = 99991;
    private String[] hashTable;
    private int total = 0;

    public HashTable() {
        hashTable = new String[size];
        /*for (int i = 0; i < size; i++) {
            hashTable[i] = "";
        }*/ 
    }

    public boolean check(int slot, String check) {
        if(check == null)
            return false; 
        if (hashTable[slot].equals(check)) {
            return true;
        } 
        else {
            total++;
            return false;
        }
    }

    public void set(int slot, String word) {
        hashTable[slot] = word;
    }

    public int gettotal() {
        return total;
    }

    public boolean isEmpty(int slot){
        return hashTable[slot]==null; 
    }
}

class Solution {

    HashTable mytable = new HashTable();
    public Solution(HashTable input) {

        mytable = input;
        //this is the constructor

    }

    public int find(String word) {

        //fill this in so as to minimize collisions
        //this method should return the slot in the HashTable
       // where the word is

        return hash(word);
    }

    public void fill(String[] array) {

        //fill this in using some hashing strategy
        //this should add all the words in the array into the
        //HashTable

        for(String s: array){
            if(s==null)
                continue;
            int code = hash(s); 

            while(true){
                if(mytable.isEmpty(code))
                    break; 
                code = (code + 1)%99991; 
            }
            mytable.set(code,s); 
        }
    }

    public int hash(String s){
        int hash = 0;
        int R = 31;
        int M = 99991; 

        if(s==null)
            return 0; 

        try{
        for (int i = 0; i < s.length(); i++)
            hash = (R * hash + s.charAt(i)) % M;

        }

    catch(java.lang.NullPointerException e){
        System.out.println(s); 
    }
         
        return hash; 
    }
}