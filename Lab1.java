import java.util.*; 
import java.math.*; 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Main {
  public static void main(String[] args)throws NoSuchAlgorithmException {
    //public VeLUxsKj5iHXWD8RHYHeg3PR2zKkZaZmuw

    
    String result ="80" + genRandomHex(); 
    //System.out.println(result); 
    SHA256 sha = new SHA256(); 
    
    String preHash = result; 
    
    result = sha.sha256(result); 
    
     //System.out.println(result); 
     
     result = sha.sha256(result); 
     
     //System.out.println(result); 
     
     String firstEight = result.substring(0,8); 
    
    preHash += firstEight; 
    
    //preHash += firstEight; 
    
    //System.out.println(preHash); 
    
    //BigInteger dec = hexadecimalToDecimal(preHash); 
     //BigInteger n = new BigInteger(Integer.toString(Integer.parseInt(preHash, 16)));

   // System.out.println(encode(n.toString())); 
   
   //System.out.println(hexadecimalToDecimal(preHash));
   
   BigInteger n = new BigInteger(preHash, 16); 
   System.out.println(base58(n));
   
  }
  

    

    
  static String base58(BigInteger n){
    String aplphabet = "123456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ"; 
    String result = ""; 
    while(n.compareTo(new BigInteger("58")) > 0){
      //int mod = n % 58; 
      BigInteger mod = n.mod(new BigInteger("58")); 
      
      result = aplphabet.charAt(mod.intValue()) + result; 
      
      //n = n / 58;
      n = n.divide(new BigInteger("58")); 
    }
    
    if(n.compareTo(new BigInteger("0")) > 0){
      result = aplphabet.charAt(n.intValue()) + result; 
    }
    
    return result; 
  }
  
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
