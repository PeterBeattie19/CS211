import java.util.*; 
import java.math.*; 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Main {
  public static void main(String[] args)throws NoSuchAlgorithmException {
    //public Key VeLUxsKj5iHXWD8RHYHeg3PR2zKkZaZmuw

    
    String result ="80" + genRandomHex(); //Generate 64 digit random hexadecimal string and append "80" onto the front
    
    SHA256 sha = new SHA256(); 
    
    String preHash = result; //Remember the original string (the 64 digit hexadecimal with 80 @ the front). 
    
    result = sha.sha256(sha.sha256(result)); // First and second hash, store it in the string result 
                   
    String firstEight = result.substring(0,8); // take first 8 digits of the doubly hashed hexadecimal stirng. 
    
    preHash += firstEight; //append the first eight digits onto the end of our original 64 digits headecimal string with "80" at the front
   
   BigInteger n = new BigInteger(preHash, 16); //Create a new Big Integer with an initial value of the prehash string converted to hexadecimal. 
   System.out.println(base58(n)); //Print the base  58 conversion of the BigInteger n. 
   
  }
  

    

    
  static String base58(BigInteger n){
    String aplphabet = "123456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";  //Look up table
    String result = ""; //Where we store our result 
    while(n.compareTo(new BigInteger("58")) > 0){ //while n is greater than 58 
      
      BigInteger mod = n.mod(new BigInteger("58")); //Divide 58 into the string and get the remainder 
      
      result = aplphabet.charAt(mod.intValue()) + result; //look up the character at position "mod", and append it onto the end of the result string
      
      n = n.divide(new BigInteger("58")); //Re-assign n to be n/58; 
    }
    
    if(n.compareTo(new BigInteger("0")) > 0){ //if n is not zero then just look up the character at position "n" and append it onto result.
      result = aplphabet.charAt(n.intValue()) + result; 
    }
    
    return result; 
  }
  
  
  //PHIL CODE 
  public static String genRandomHex(){
    Random ran = new Random();
    char character[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    String result = ""; 
    
    for(int i = 0; i<64; i++){
      result += character[ran.nextInt(16)]; 
    }
    
    return result; 
  }
  

public static class SHA256 {

  public static String sha256(String input) throws NoSuchAlgorithmException {
    byte[] in = hexStringToByteArray(input);
    MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
    byte[] result = mDigest.digest(in);
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < result.length; i++) {
      sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
    }
 return sb.toString();
}

  public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
      + Character.digit(s.charAt(i+1), 16));
    }
    return data;
 }
}
  
}
