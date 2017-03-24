package geoip2;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

/**
 * @Class GeoIP2WebSvcTest.java
 * @author eWIDEPLUS
 * @since 2017-03-20
 */

public class GeoIP2WebSvcTest {

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
    		geoIP2Websvc(ip[0]);
    		System.out.println("\n");
	    	
	    }
	}
	
	public static void geoIP2Websvc(String ip) throws IOException, GeoIp2Exception{
		
		int userId = 1234;
		String licenseKey =  "yourLicenseKey";
		
		try (WebServiceClient client = new WebServiceClient.Builder(userId, licenseKey)
		        .build()) {

		    InetAddress ipAddress = InetAddress.getByName(ip);

		    // Do the lookup
		    CityResponse response = client.city(ipAddress);

		    Country country = response.getCountry();
		    System.out.println(country.getIsoCode());            // 'US'
		    System.out.println(country.getName());               // 'United States'
		    System.out.println(country.getNames().get("zh-CN")); // '美国'

		    Subdivision subdivision = response.getMostSpecificSubdivision();
		    System.out.println(subdivision.getName());       // 'Minnesota'
		    System.out.println(subdivision.getIsoCode());    // 'MN'

		    City city = response.getCity();
		    System.out.println(city.getName());       // 'Minneapolis'

		    Postal postal = response.getPostal();
		    System.out.println(postal.getCode());       // '55455'

		    Location location = response.getLocation();
		    System.out.println(location.getLatitude());        // 44.9733
		    System.out.println(location.getLongitude());       // -93.2323
		}
	}

}


