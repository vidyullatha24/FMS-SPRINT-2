package com.capg.as.service;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.as.dto.Airport;

@SpringBootTest
class TestAirportService 
{
	 @Autowired
     AirportService airportService;
     
     @Test
     public void testGetAirport_Positive() throws Exception
     {
   	    Optional<Airport> airport = airportService.getAirport("Goa");
   	    Assertions.assertEquals(true,airport.isPresent());
     }
     
     @Test
     public void testGetAirport_Negative() throws Exception
     {
   	    Optional<Airport> airport = airportService.getAirport("xyz");
   	    Assertions.assertEquals(false,airport.isPresent());
     }
     
     @Test
     public void testAddAirport_Positive() throws Exception
     {
   	  Airport airport = new Airport("Chennai","Meenambakkam","Chennai");
   	  Airport a = airportService.addAirport(airport);
   	  Assertions.assertEquals(a.getAirportId(), airport.getAirportId());
     }
     
     @Test
     public void testDeleteAirport_Positive() throws Exception
     {
    	  airportService.deleteAirport("Chennai");
    	  Optional<Airport> a = airportService.getAirport("Chennai");
    	    Assertions.assertEquals(false,a.isPresent());
     }
}
