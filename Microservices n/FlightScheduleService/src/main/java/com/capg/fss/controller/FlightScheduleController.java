package com.capg.fss.controller;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.capg.fss.dto.FlightSchedule;
import com.capg.fss.service.FlightScheduleService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FlightScheduleController 
{
	@Autowired
	FlightScheduleService flightScheduleService;
	public void setFlightScheduleService(FlightScheduleService flightScheduleService)
	{
		this.flightScheduleService = flightScheduleService;
	}
	@GetMapping(value="/getFlightSchedule/{scheduleId}",produces="application/json")
	public ResponseEntity<Optional<FlightSchedule>> getFlightSchedule(@PathVariable String scheduleId)
	{
		Optional<FlightSchedule> flightSchedule =  flightScheduleService.getFlightSchedule(scheduleId);
		if(flightSchedule.isPresent())
			return new ResponseEntity<Optional<FlightSchedule>>(flightSchedule,HttpStatus.OK);
		return new ResponseEntity<Optional<FlightSchedule>>(flightSchedule,HttpStatus.NOT_FOUND);
	}
	@GetMapping(value="/getFlightSchedules",produces="application/json")
	public List<FlightSchedule> getAllFlightSchedules()
	{
		return flightScheduleService.getAllFlightSchedules();
	}
	@PostMapping(value="/addFlightSchedule",consumes="application/json")
	public ResponseEntity<String> addFlightSchedule(@RequestBody()FlightSchedule flightSchedule)
	{
		try
		{
			flightScheduleService.addFlightSchedule(flightSchedule);
			return new ResponseEntity<String>("{}",HttpStatus.OK);
		}
		catch (Exception ex) 
		{
			return new ResponseEntity<String>(ex.getMessage()+"Failed to add",HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping(value="/updateFlightSchedule",consumes="application/json")
	public void updateFlightSchedule(@RequestBody() FlightSchedule flightSchedule)
	{
		flightScheduleService.updateFlightSchedule(flightSchedule);
	} 
	@DeleteMapping("/deleteFlightSchedule/{scheduleId}")
	public ResponseEntity<String> deleteFlightSchedule(@PathVariable String scheduleId)
	{
		try
		{
			flightScheduleService.deleteFlightSchedule(scheduleId);
			return new ResponseEntity<String>("{}",HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<String>("Deletion Failed",HttpStatus.BAD_REQUEST);
		}
	}
}
