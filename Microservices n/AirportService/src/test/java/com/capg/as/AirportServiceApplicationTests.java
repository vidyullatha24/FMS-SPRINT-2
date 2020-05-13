package com.capg.as;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.capg.as.dto.Airport;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class AirportServiceApplicationTests 
{
		@Autowired
		TestRestTemplate testRestTemplate;
		public void setTestRestTemplate(TestRestTemplate testRestTemplate)
		{
			this.testRestTemplate=testRestTemplate;
		}
		@LocalServerPort
		int localServerPort;
		@Test
		public void testGetAirport_Positive() throws Exception
		{
			 String url="http://localhost:"+localServerPort+"getAirport/Delhi";
			 ResponseEntity<Airport> airport = testRestTemplate.getForEntity(url,Airport.class);
			 Assertions.assertEquals(200, airport.getStatusCodeValue());
		}
		
		@Test
		public void testGetAirport_Negative() throws Exception
		{
			 String url="http://localhost:"+localServerPort+"getAirport/Pune";
			 ResponseEntity<Airport> airport = testRestTemplate.getForEntity(url,Airport.class);
			 Assertions.assertEquals(404, airport.getStatusCodeValue());
		}
		
		/*@Test
		public void testGetAirports_Positive() throws Exception
		{
			 String url="http://localhost:"+localServerPort+"getAirports";
			 ResponseEntity<Airport> airport = testRestTemplate.getForEntity(url,Airport.class);
			 Assertions.assertEquals(200, airport.getStatusCodeValue());
		} */
		
		@Test
		public void testAddAirport_Positive() throws Exception
		{
			 String url="http://localhost:"+localServerPort+"addAirport";
			 Airport airport = new Airport("Goa","Dabolim","Goa");
			 ResponseEntity<String> response = testRestTemplate.postForEntity(url,airport,String.class);
			 Assertions.assertEquals(200, response.getStatusCodeValue());
		}
		@Test
		public void testAddAirport_Negative() throws Exception
		{
			 String url="http://localhost:"+localServerPort+"addAirport";
			 Airport airport = null;
			 ResponseEntity<String> response = testRestTemplate.postForEntity(url,airport,String.class);
			 Assertions.assertEquals(415, response.getStatusCodeValue());
		}
		@Test
		public void testDeleteAirport_Positive() throws Exception
		{
			 String url="http://localhost:"+localServerPort+"deleteAirport/Kerala";
			 HttpEntity<String> ent = new HttpEntity<>("Kerala");
			  ResponseEntity<String> response = testRestTemplate.exchange(url,HttpMethod.DELETE,null,String.class);
			 Assertions.assertEquals(200, response.getStatusCodeValue());
		}
		@Test
		public void testDeleteAirport_Negative() throws Exception
		{
			 String url="http://localhost:"+localServerPort+"deleteAirport/Kerala";
			 HttpEntity<String> ent = new HttpEntity<>("Kerala");
			  ResponseEntity<String> response = testRestTemplate.exchange(url,HttpMethod.DELETE,null,String.class);
			 Assertions.assertEquals(404, response.getStatusCodeValue());
		}
	}











