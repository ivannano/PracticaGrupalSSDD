package es.codeurjc.PracticaGrupalSSDD_1;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.codeurjc.PracticaGrupalSSDD_1.Estaciones.Station;
import es.codeurjc.PracticaGrupalSSDD_1.Estaciones.StationRepository;




@Component
@Profile("local")
public class DataBase {
	
	@Autowired
	private StationRepository stationRepo;

	@PostConstruct
	public void init() {

		// Sample films

		stationRepo.save(new Station("124",12.3,100));

	}
}
