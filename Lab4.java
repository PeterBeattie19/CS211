import java.util.*; 
import java.io.*; 

//without using a trie searching for solutions takes approx. 6000 milliseconds
//If yiu use a Trie searching takes 15 milliseconds 

public class Lab5{

	public static HashSet<String> set = new HashSet<>(); 
	public static void main (String args[]) throws IOException{

		File f = new File("dictionary.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(f)); 

		HashSet<String> dict = new HashSet<>(); 
		Node trie = new Node(); 

		String line = br.readLine();

		while(line!=null){
			dict.add(line);
			trie.add(line,0);
			line = br.readLine(); 
		}
		   
		System.out.println(dict.size());
		int n = 4; 
			
		String board[] = new String[n]; 
		int visited[][] = new int[n][n]; 

		board[0] = "rhre";
		board[1] = "ypcs";
		board[2] = "wnsn";
		board[3] = "tego";

		PriorityQueue<String> q = new PriorityQueue<>(new Comparator<String>() {
			public int compare(String x, String y) {
				return y.length() - x.length(); 
			}
		});

		br = new BufferedReader(new InputStreamReader(System.in)); 
		for(int i = 0; i<n; i++)
			board[i] = br.readLine(); 

		long start = System.currentTimeMillis(); 
		for(int i = 0; i<n; i++){
			for(int j = 0; j<n; j++){
				dfs(i,j, "",board,dict,visited,q,trie); 
				set(visited); //Reset visited to zero.
			}
		}

		long end = System.currentTimeMillis(); 

		System.out.println("Time for Trie " +(end-start)); 
		//System.out.println("Time for no Trie " +(end-start)); 
  
		for(int i = 0; i<10; i++){
			String pop = q.poll();
			System.out.println(pop +" "+ pop.length());
		}
		System.out.println(set.size());
	}

	public static void set(int arr[][]){
		for(int i = 0; i<arr.length; i++)
			for(int j = 0; j<arr.length; j++)
				arr[i][j] = 0; 
	}

	public static void dfs(int i, int j, String s,String board[], HashSet<String> dict, int visited[][], PriorityQueue<String> q, Node trie){

		s += board[i].substring(j,j+1); 
		if(dict.contains(s)){
			q.add(s);
			set.add(s);
		}

			visited[i][j] = 1;

			if(trie.findCount(s,0) != 0){ //if no words start with the prefix s then there is no need to recurse 

			if((i-1)>=0  && visited[i-1][j]==0)
				dfs(i-1,j,s,board,dict,visited,q,trie);

			if((i+1)<board.length  && visited[i+1][j]==0)
				dfs(i+1,j,s,board,dict,visited,q,trie);

			if((j-1)>=0 && visited[i][j-1]==0)
				dfs(i,j-1,s,board,dict,visited,q,trie);

			if((j+1)<board.length && visited[i][j+1]==0)
				dfs(i,j+1,s,board,dict,visited,q,trie);

			if((i-1)>=0 && (j+1)<board[i].length() && visited[i-1][j+1]==0)
				dfs(i-1,j+1,s,board,dict,visited,q,trie);

			if((i-1)>=0 && (j-1)>=0 && visited[i-1][j-1]==0)
				dfs(i-1,j-1,s,board,dict,visited,q,trie);

			if((i+1)<board.length && (j-1)>=0 && visited[i+1][j-1]==0)
				dfs(i+1,j-1,s,board,dict,visited,q,trie);

			if((i+1)<board.length  && (j+1)<board[0].length() && visited[i+1][j+1]==0)
				dfs(i+1,j+1,s,board,dict,visited,q,trie);
			}

		visited[i][j] = 0; //Reset the path taken.
	}

public static class Node {
  private int numOfChars = 26;
  Node children[] = new Node[numOfChars];
  int size = 0;

  public void add(String s, int index){
     size++;
     if(index == s.length()) return;

     int intVal = toInt(s.charAt(index));
     Node child = children[intVal];

     if(child==null){
       child = new Node();
       children[intVal] = child; 
     }

     child.add(s, index+1);
  }

  public int findCount(String s, int index){ //returns how many words start with the prefix s. 
    if(index == s.length()) return size;

    Node child = children[toInt(s.charAt(index))];  

    if(child == null){
      return 0;
    }

    return child.findCount(s, index + 1);
  }

  public int toInt(char c){
    return (c - 'a');
  }
}

} 
