package es.codeurjc.PracticaGrupalSSDD_1;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.codeurjc.PracticaGrupalSSDD_1.Bicicletas.BicycleRepository;
import es.codeurjc.PracticaGrupalSSDD_1.Bicicletas.Bicycle;

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
	@Autowired
	private BicycleRepository bycicleRepo;
	
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
		
		bycicleRepo.save(new Bicycle("abc123def456aa11", "ROCKRIDER RACE 900 29"));
		bycicleRepo.save(new Bicycle("abc123def456bb22", "ROCKRIDER RACE 900 29"));
		bycicleRepo.save(new Bicycle("abc123def456cc33", "ROCKRIDER RACE 900 29"));

	}
}
