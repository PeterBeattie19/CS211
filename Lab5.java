import java.util.*; 
import java.io.*; 
import javax.sound.sampled.*;

public class Lab6{


	public static void tone(int hz, int msecs, double vol) throws LineUnavailableException{
		float SAMPLE_RATE = 8000f;
		byte[] buf = new byte[1];
		AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,true,false);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl.open(af);
		sdl.start();

		for(int i=0; i < msecs*8; i++) {
			double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
			buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
			sdl.write(buf,0,1);
		}

		sdl.drain();
		sdl.stop();
		sdl.close();
	}	

	public static void main (String args[]) throws LineUnavailableException, IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

		String message = br.readLine(); 



		//sample signal: llsl which means long long short long
		String lookup[] = {"sl", "lsss","lsls","lss","s","ssls","lls","ssss","ss","slll","lsl","slss","ll","ls","lll","slls","llsl","sls","sss","l","ssl","sssl","sll","lssl","lsll","llss"};

		playMessage(message,lookup); 

	}


	public static void playMessage(String s, String arr[]) throws LineUnavailableException{
		for(int i = 0; i<s.length(); i++){
				if(s.charAt(i) == ' '){
					System.out.print(" "); 
					tone(1000,600,0);
					continue;
				}

				String mc = arr[s.charAt(i) - 'a']; 


			for(int j = 0; j<mc.length(); j++){
				if(mc.charAt(j) == 's'){
					System.out.print(".");
					tone(1000,100,8.0);
				}
				else{
					System.out.print("-");
					tone(1000,200,8.0);
				}
			} 
		}
	}
} 
