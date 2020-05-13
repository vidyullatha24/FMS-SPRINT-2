package com.capg.fss.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capg.fss.dao.FlightScheduleDAO;
import com.capg.fss.dto.FlightSchedule;

@Service
public class FlightScheduleService 
{
	@Autowired
	FlightScheduleDAO fsdao;
	public void setFsdao(FlightScheduleDAO fsdao) { this.fsdao=fsdao; }

	@Transactional(readOnly=true)
	public Optional<FlightSchedule> getFlightSchedule(String scheduleId)
	{
		return fsdao.findById(scheduleId);
	}

	@Transactional(readOnly=true)
	public List<FlightSchedule> getAllFlightSchedules()
	{
		return fsdao.findAll(Sort.by(Sort.Direction.ASC, "scheduleId"));
	}

	@Transactional
	public FlightSchedule addFlightSchedule(FlightSchedule flightSchedule)
	{
		return fsdao.save(flightSchedule);
	}

	@Transactional
	public void updateFlightSchedule(FlightSchedule flightschedule)
	{
		fsdao.save(flightschedule);
	}

	@Transactional
	public void deleteFlightSchedule(String scheduleId)
	{
		fsdao.deleteById(scheduleId);
	}
}
