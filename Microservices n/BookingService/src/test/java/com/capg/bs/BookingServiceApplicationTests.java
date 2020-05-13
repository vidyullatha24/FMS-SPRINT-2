package com.capg.bs;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.capg.bs.dto.Booking;

@SpringBootTest
class BookingServiceApplicationTests {

	@Test
	public void getBookingDetails() throws URISyntaxException {
		RestTemplate restTemplate=new RestTemplate();
		final String Url= "http://localhost:8095/getBookingDetails/16";
		URI uri=new URI(Url);
		 ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		    String booking = result.getBody();
		    Assertions.assertEquals(200, result.getStatusCodeValue());
		   System.out.println(booking);
		    Assertions.assertNotNull(booking);
		    }
	@Test
	public void  getAllBookingDetails() throws Exception
	{
		RestTemplate rest=new RestTemplate();
		final String baseUrl ="http://localhost:8095/getAllBookingDetails";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result=rest.getForEntity(uri,String.class);
		String booking = result.getBody();
		Assertions.assertEquals(200, result.getStatusCodeValue());	
		System.out.println(booking);
		Assertions.assertNotNull(booking);
	}
	@Test
	public void deleteBookingDetails() throws Exception
	{
		RestTemplate rest=new RestTemplate();
		final String baseUrl ="http://localhost:8095/deleteBookingDetails/16";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result=rest.exchange(uri, HttpMethod.DELETE,null,String.class);
		System.out.println(result);
		Assertions.assertEquals(200, result.getStatusCodeValue());
		
	}
	

}
