package geoip2;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;


public class GeoIP2DBTest {

	public static void main(String[] args) throws IOException, GeoIp2Exception {
		
		String inputIP = "data/ipList.csv";
		String splitter = ",";
		
		File file = new File(inputIP);
	    List<String> lines = Files.readAllLines(file.toPath(), 
	            StandardCharsets.UTF_8);
	    
	  //processing each line of input
	    for (String line : lines) {
	    	String [] ip = line.split(splitter);
    		System.out.println("Ip Address: "+ip[0]);
    		System.out.println("site: "+ip[1]);
    		System.out.println("=======================");
    		getGeoInfo(ip[0]);
    		System.out.println("\n");
	    	
	    }
		
	}
	
	public static void getGeoInfo(String testIp) throws IOException, GeoIp2Exception{
		
		// A File object pointing to your GeoIP2 or GeoLite2 database
		File database = 
				 new File("data/GeoLite2-City.mmdb"); 
		
		DatabaseReader reader = new DatabaseReader.Builder(database).build();
		
		//String testIp = "216.58.200.14";
		InetAddress ipAddress = InetAddress.getByName(testIp);
		
		// Replace "city" with the appropriate method for your database, e.g.,
		// "country".
		CityResponse response = reader.city(ipAddress);

		Country country = response.getCountry();
		System.out.println("Iso Code: "+country.getIsoCode());      // ex: 'US'
		System.out.println("Country: "+country.getName());          // ex: 'United States'

		Subdivision subdivision = response.getMostSpecificSubdivision();
		System.out.println("subdivision Name: "+subdivision.getName());    // ex: 'Minnesota'
		System.out.println("subdivision ISO code: "+subdivision.getIsoCode()); // ex: 'MN'

		City city = response.getCity();
		System.out.println("city: "+city.getName()); // ex: 'Minneapolis'

		Postal postal = response.getPostal();
		System.out.println("post code: "+postal.getCode()); // '55455'

		Location location = response.getLocation();
		System.out.println("latitude: "+location.getLatitude());  // 44.9733
		System.out.println("longitude: "+location.getLongitude()); // -93.2323
	
	}

}
