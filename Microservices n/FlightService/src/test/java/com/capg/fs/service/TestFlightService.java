package com.capg.fs.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.fs.dto.Flight;

@SpringBootTest
class TestFlightService {

	 @Autowired
     UsersService flightService;
     
     @Test
     public void testGetFlight_Positive() throws Exception
     {
   	    Optional<Flight> flight = flightService.getFlight("152");
   	    Assertions.assertEquals(true,flight.isPresent());
     }
     
     @Test
     public void testGetFlightSchedule_Negative() throws Exception
     {
   	    Optional<Flight> flight = flightService.getFlight("xyz");
   	    Assertions.assertEquals(false,flight.isPresent());
     }
     
     @Test
     public void testAddFlight_Positive() throws Exception
     {
    	 Flight flight = new Flight("151","Vistara",120);

Flight f = flightService.addFlight(flight);
   	  Assertions.assertEquals(f.getFlightNo(), flight.getFlightNo());
     }
     
     @Test
     public void testDeleteFlightSchedule_Positive() throws Exception
     {
    	  flightService.deleteFlight("153");
    	  Optional<Flight> f = flightService.getFlight("153");
    	    Assertions.assertEquals(false,f.isPresent());
     }

}
