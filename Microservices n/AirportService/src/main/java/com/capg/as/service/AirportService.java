package com.capg.as.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capg.as.dao.AirportDAO;
import com.capg.as.dto.Airport;

@Service
public class AirportService 
{
	@Autowired
	AirportDAO adao;
	public void setAdao(AirportDAO adao) { this.adao=adao;}

	@Transactional(readOnly=true)
	public Optional<Airport> getAirport(String airportId)
	{
		return adao.findById(airportId);
	}
	@Transactional(readOnly=true)
	public List<Airport> getAirports()
	{
		return adao.findAll();
	}
	@Transactional
	public Airport addAirport(Airport airport)
	{
		return adao.save(airport);
	}
	@Transactional
	public void deleteAirport(String airportId)
	{
		adao.deleteById(airportId);
	}	
}
