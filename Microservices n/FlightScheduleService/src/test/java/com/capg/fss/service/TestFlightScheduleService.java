package com.capg.fss.service;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.capg.fss.dto.Airport;
import com.capg.fss.dto.Flight;
import com.capg.fss.dto.FlightSchedule;

@SpringBootTest
class TestFlightScheduleService {

	@Autowired
	FlightScheduleService flightScheduleService;

	@Test
	public void testGetFlightSchedule_Positive() throws Exception
	{
		Optional<FlightSchedule> flightSchedule = flightScheduleService.getFlightSchedule("147");
		Assertions.assertEquals(true,flightSchedule.isPresent());
	}

	@Test
	public void testGetFlightSchedule_Negative() throws Exception
	{
		Optional<FlightSchedule> flightSchedule = flightScheduleService.getFlightSchedule("xyz");
		Assertions.assertEquals(false,flightSchedule.isPresent());
	}

	@Test
	public void testAddFlightSchedule_Positive() throws Exception
	{
		Flight flight = new Flight("151","Spicejet",120);
		Airport source = new Airport("Goa","Dabolim","Goa");
		Airport destination = new Airport("Hyderabad","Rajiv Gandhi","Hyderabad");
		LocalDateTime departure = LocalDateTime.of(2020, 2, 13, 15, 56, 00);
		LocalDateTime arrival = LocalDateTime.of(2020, 2, 13, 17, 00, 00);    
		FlightSchedule flightSchedule = new FlightSchedule("151",flight,source,destination,200.0,departure,arrival);
		FlightSchedule fs = flightScheduleService.addFlightSchedule(flightSchedule);
		Assertions.assertEquals(fs.getScheduleId(), flightSchedule.getScheduleId());
	}

	@Test
	public void testDeleteFlightSchedule_Positive() throws Exception
	{
		flightScheduleService.deleteFlightSchedule("146");
		Optional<FlightSchedule> a = flightScheduleService.getFlightSchedule("146");
		Assertions.assertEquals(false,a.isPresent());
	}
}
