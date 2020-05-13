package com.capg.as.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.capg.as.dto.Airport;
import com.capg.as.service.AirportService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AirportController 
{
	@Autowired
	AirportService airportService;
	public void setFlightService(AirportService airportService)
	{
		this.airportService=airportService;
	}
	@GetMapping(value="/getAirport/{airportId}",produces="application/json")
	public ResponseEntity<Optional<Airport>> getAirport(@PathVariable String airportId)
	{
		Optional<Airport> airport =  airportService.getAirport(airportId);
		if(airport.isPresent())
			return new ResponseEntity<Optional<Airport>>(airport,HttpStatus.OK);
		return new ResponseEntity<Optional<Airport>>(airport,HttpStatus.NOT_FOUND);
	}
	@GetMapping(value="/getAirports",produces="application/json")
	public List<Airport> getAirports()
	{		
		return airportService.getAirports();
	}
	@PostMapping(value="/addAirport",consumes="application/json")
	public ResponseEntity<String> addAirport(@RequestBody()Airport airport)
	{
		try
		{
			airportService.addAirport(airport);
			return new ResponseEntity<String>("Airport details added",HttpStatus.OK);
		}
		catch (Exception ex) 
		{
			return new ResponseEntity<String>(ex.getMessage()+"Failed to add",HttpStatus.BAD_REQUEST);
		}

	}

	/*@PutMapping(value="/updateAirport",consumes="application/json")
     public void updateAirport(@RequestBody() Airport airport)
     {
   	    airportService.updateAirport(airport);
     } */
	@DeleteMapping("/deleteAirport/{airportId}")
	public ResponseEntity<String> deleteAirport(@PathVariable String airportId)
	{
		try
		{
			airportService.deleteAirport(airportId);
			return new ResponseEntity<String>("Airport Details Deleted Successfully",HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<String>("Deletion Failed",HttpStatus.BAD_REQUEST);
		}
	}

}
