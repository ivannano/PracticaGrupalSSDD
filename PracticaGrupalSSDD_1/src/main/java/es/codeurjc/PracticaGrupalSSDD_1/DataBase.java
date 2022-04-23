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
	private BicycleRepository bicycleRepo;
	
	@PostConstruct
	public void init() {
		//Usuarios
		userRepo.save(new User(123L, "Pepe", "password"));
		userRepo.save(new User(101L, "David", "password1"));
		userRepo.save(new User(102L, "Valor", "password2"));
		userRepo.save(new User(103L, "Ivan", "password3"));
		userRepo.save(new User(104L, "Mario", "password4"));
		
		//Estaciones
		Station s1 = stationRepo.save(new Station("124","52° 31' 28'' N; 13° 24' 38'' E",5));
		Station s2 = stationRepo.save(new Station("300","48° 51' 12'' N; 02° 20' 56'' E",10));
	
		//Bicicletas
		Bicycle b1 = bicycleRepo.save(new Bicycle("abc123def456aa11", "ROCKRIDER RACE 900 29"));
		Bicycle b2 = bicycleRepo.save(new Bicycle("abc123def456bb22", "ROCKRIDER RACE 900 20"));
		Bicycle b3 = bicycleRepo.save(new Bicycle("abc123def456cc33", "ROCKRIDER RACE 900 18"));
		Bicycle b4 = bicycleRepo.save(new Bicycle("abc123def456aa44", "ROCKRIDER RACE 900 32"));
		Bicycle b5 = bicycleRepo.save(new Bicycle("abc123def456aa55", "ROCKRIDER RACE 900 45"));
		Bicycle b6 = bicycleRepo.save(new Bicycle("abc123def456aa66", "ROCKRIDER RACE 100 00"));
		Bicycle b7 = bicycleRepo.save(new Bicycle("abc123def456aa77", "ROCKRIDER RACE 200 11"));
		Bicycle b8 = bicycleRepo.save(new Bicycle("abc123def456aa88", "ROCKRIDER RACE 300 22"));
		Bicycle b9 = bicycleRepo.save(new Bicycle("abc123def456aa99", "ROCKRIDER RACE 400 33"));
		
		//Asignacion de bicicletas a estaciones
		
		//Primera Estacion
		bicycleRepo.updateEstacionById(s1,b1.getId());
		bicycleRepo.updateEstadoEnBaseById(b1.getId());
		
		bicycleRepo.updateEstacionById(s1,b2.getId());
		bicycleRepo.updateEstadoEnBaseById(b2.getId());
		
		bicycleRepo.updateEstacionById(s1,b3.getId());
		bicycleRepo.updateEstadoEnBaseById(b3.getId());
		
		bicycleRepo.updateEstacionById(s1,b4.getId());
		bicycleRepo.updateEstadoEnBaseById(b4.getId());
		
		
		//Segunda Estacion
		
		bicycleRepo.updateEstacionById(s2,b5.getId());
		bicycleRepo.updateEstadoEnBaseById(b5.getId());
		
		bicycleRepo.updateEstacionById(s2,b6.getId());
		bicycleRepo.updateEstadoEnBaseById(b6.getId());
		
		bicycleRepo.updateEstacionById(s2,b7.getId());
		bicycleRepo.updateEstadoEnBaseById(b7.getId());
		
		
		
		
		
		
		
	}
}
