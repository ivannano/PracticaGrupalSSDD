package es.codeurjc.Estaciones;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class StationService {
	
	private StationRepository repository;
	
	public StationService(StationRepository repo) {
		this.repository = repo;
	}
	
	public Station save(Station station) {
		Station newStation = repository.save(station);
		return newStation;
	}
	
	public void delete(Long Id) {
		repository.deleteById(Id);
	}
	
	
	public List<Station> findAll() {
		return repository.findAll();
	}
	
	public Optional<Station> findOne(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}
}
