import java.util.*; 
import java.io.*; 

public class Lab7{
	public static void main (String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int p,g,x,c1,c2,gxp;

		x = 0;



		String line[] = br.readLine().split(" ");

		p = Integer.parseInt(line[0]);
		g = Integer.parseInt(line[1]);
		gxp = Integer.parseInt(line[2]);

		line = br.readLine().split(" "); 

		c1 = Integer.parseInt(line[0]);
		c2 = Integer.parseInt(line[1]);
		


		for(int i = 0; i<=p; i++){
			if(modPow(g,i,p) == gxp){
				x = i; 
				break; 
			}
		}

		long part1 = modPow(c1,(p-1-x),p); 

		long ans = modMult(part1,c2,p); 

		System.out.println(ans); 
		//System.out.println(x); 
	}

public static long modPow(long number, long power, long modulus) {
    //raises a number to a power with the given modulus
    //when raising a number to a power, the number quickly becomes too large to
   // handle
    //you need to multiply numbers in such a way that the result is consistently
    //moduloed to keep it in the range
    //however you want the algorithm to work quickly - having a multiplication
    //loop would result in an O(n) algorithm!
        //the trick is to use recursion - keep breaking the problem down into smaller
      //  pieces and use the modMult method to join them back together
    if (power == 0)
        return 1;
    else if (power % 2 == 0) {
        long halfpower = modPow(number, power / 2, modulus);
        return modMult(halfpower, halfpower, modulus);
    } else {
        long halfpower = modPow(number, power / 2, modulus);
        long firstbit = modMult(halfpower, halfpower, modulus);
        return modMult(firstbit, number, modulus);
    }
}

public static long modMult(long first, long second, long modulus) {
    //multiplies the first number by the second number with the given modulus
    //a long can have a maximum of 19 digits. Therefore, if you're multiplying
   // two ten digits numbers the usual way, things will go wrong
    //you need to multiply numbers in such a way that the result is consistently
    //moduloed to keep it in the range
    //however you want the algorithm to work quickly - having an addition loop
    //would result in an O(n) algorithm!
        //the trick is to use recursion - keep breaking down the multiplication into
      //  smaller pieces and mod each of the pieces individually
    if (second == 0)
        return 0;
    else if (second % 2 == 0) {
        long half = modMult(first, second / 2, modulus);
        return (half + half) % modulus;
    } else {
        long half = modMult(first, second / 2, modulus);
        return (half + half + first) % modulus;
    }
}


} 
