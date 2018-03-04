import java.util.*; 
import java.io.*; 

public class Lab4{
	public static void main (String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

		int size = 	Integer.parseInt(br.readLine()); 
		
		String arr[] = new String[size];

		for(int i = 0; i<size; i++)
			arr[i] = br.readLine(); 

		quickSort(arr,0,size-1); 


		for(String s: arr)
			System.out.print(s +" "); 
		System.out.println(); 

	}

	public static void swap(int i, int j,String arr[]){
		String temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp; 
	}


	public static char greatestCharacter(String s){
		char g = s.charAt(0);

		for(int i = 1; i<s.length(); i++)
			if(s.charAt(i) > g)
				g = s.charAt(i);

		return g;
	}

	public static boolean compare(String s1, String s2){
		char g1 = greatestCharacter(s1);
		char g2 = greatestCharacter(s2); 

		if(g1==g2){
			if(s2.compareTo(s1)>0)
				return true;
			
			return false;
		}

		else return g1<=g2; 
	}

  public static void quickSort(String arr[], int low, int high){
    String pivot = arr[low + (high-low)/2];
    int i = low;
    int j = high;

    while(i<=j){

      while(compare(arr[i],pivot)){
        i++;
      }

      while(compare(pivot,arr[j])){
        j--;
      }

      if(i<=j){
        swap(i,j,arr);
        i++;
        j--;
        //print();
      }
    }

    if(low < j){
      quickSort(arr, low, j);
    }

    if(high>i){
      quickSort(arr, i, high);
    }
  }
} 
