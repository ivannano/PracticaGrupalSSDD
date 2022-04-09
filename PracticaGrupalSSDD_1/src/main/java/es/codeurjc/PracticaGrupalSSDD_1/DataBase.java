package es.codeurjc.PracticaGrupalSSDD_1;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.codeurjc.PracticaGrupalSSDD_1.Estaciones.Station;
import es.codeurjc.PracticaGrupalSSDD_1.Estaciones.StationRepository;

import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.User;
import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.UserRepository;


@Component
@Profile("local")
public class DataBase {
	
	@Autowired
	private StationRepository stationRepo;
	@Autowired
	private UserRepository userRepo;

	@PostConstruct
	public void init() {
		// Sample films
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		stationRepo.save(new Station("124",12.3,100));
		
		
		userRepo.save(new User(123L, "Pepe", "password"));

	}
}
