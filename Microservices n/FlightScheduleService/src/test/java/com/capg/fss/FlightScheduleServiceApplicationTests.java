package com.capg.fss;
import java.time.LocalDateTime;
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
import com.capg.fss.dto.Airport;
import com.capg.fss.dto.Flight;
import com.capg.fss.dto.FlightSchedule;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class FlightScheduleServiceApplicationTests
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
	public void testGetFlightSchedule_Positive() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"getFlightSchedule/143";
		 ResponseEntity<FlightSchedule> flightSchedule = testRestTemplate.getForEntity(url,FlightSchedule.class);
		 Assertions.assertEquals(200, flightSchedule.getStatusCodeValue());
	}
	
	@Test
	public void testGetFlightSchedule_Negative() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"getFlightSchedule/160";
		 ResponseEntity<FlightSchedule> flightSchedule = testRestTemplate.getForEntity(url,FlightSchedule.class);
		 Assertions.assertEquals(404, flightSchedule.getStatusCodeValue());
	}
	
	@Test
	public void testAddFlightSchedule_Positive() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"addFlightSchedule";
		 Flight flight = new Flight("151","Spicejet",120);
		 Airport source = new Airport("Goa","Dabolim","Goa");
		 Airport destination = new Airport("Hyderabad","Rajiv Gandhi","Hyderabad");
		 LocalDateTime departure = LocalDateTime.of(2020, 2, 13, 15, 56, 00);
		 LocalDateTime arrival = LocalDateTime.of(2020, 2, 13, 17, 00, 00);    
		 FlightSchedule flightSchedule = new FlightSchedule("147",flight,source,destination,200.0,departure,arrival);
		 ResponseEntity<String> response = testRestTemplate.postForEntity(url,flightSchedule,String.class);
		 Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	@Test
	public void testAddFlightSchedule_Negative() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"addFlightSchedule";
		 FlightSchedule flightSchedule = null;
		 ResponseEntity<String> response = testRestTemplate.postForEntity(url,flightSchedule,String.class);
		 Assertions.assertEquals(415, response.getStatusCodeValue());
	}
	@Test
	public void testDeleteFlightSchedule_Positive() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"deleteFlightSchedule/147";
		 HttpEntity<Integer> ent = new HttpEntity<>(147);
		  ResponseEntity<String> response = testRestTemplate.exchange(url,HttpMethod.DELETE,null,String.class);
		 Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	@Test
	public void testDeleteFlightSchedule_Negative() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"deleteFlightSchedule/158";
		 HttpEntity<Integer> ent = new HttpEntity<>(158);
		  ResponseEntity<String> response = testRestTemplate.exchange(url,HttpMethod.DELETE,null,String.class);
		 Assertions.assertEquals(400, response.getStatusCodeValue());
	}
}
