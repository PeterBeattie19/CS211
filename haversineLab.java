import java.util.*;
import java.io.*;

public class haversineLab  {
		public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line[] = br.readLine().split(" ");

			double lat = 53.3481005
			double lon = -6.542373399999974;
			double cityLat = Double.parseDouble(line[0]);
			double cityLon = Double.parseDouble(line[1]);

			System.out.println(haversine(lat, lon, cityLat, cityLon));
		}
		
		public static double haversine(double lat1, double lon1, double lat2, double lon2) {
			
        	double lat = Math.toRadians(lat2 - lat1);
        	double lon = Math.toRadians(lon2 - lon1);
        	lat1 = Math.toRadians(lat1);
        	lat2 = Math.toRadians(lat2);
        	double x = Math.pow(Math.sin(lat/2),2)+Math.pow(Math.sin(lon/2),2)*Math.cos(lat1)*Math.cos(lat2);
        	return 6371*(2 * Math.asin(Math.sqrt(x)));
		}
}
