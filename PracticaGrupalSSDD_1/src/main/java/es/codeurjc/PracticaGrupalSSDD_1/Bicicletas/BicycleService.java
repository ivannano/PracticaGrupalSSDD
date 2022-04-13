package es.codeurjc.PracticaGrupalSSDD_1.Bicicletas;

import java.util.List;
import java.util.Optional;

import es.codeurjc.PracticaGrupalSSDD_1.Estaciones.Station;

public class BicycleService {

	private BicycleRepository repository;
	
	public BicycleService(BicycleRepository repo) {
		repository = repo;
	}
	
	public Bicycle save(Bicycle b) {
		Bicycle newB = repository.save(b);
		return newB;
	}
	public void delete(Long Id) {
		repository.deleteById(Id);
	}
	
	
	public List<Bicycle> findAll() {
		return repository.findAll();
	}
	
	public Optional<Bicycle> findOne(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}
}
