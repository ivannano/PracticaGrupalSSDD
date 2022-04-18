package es.codeurjc.PracticaGrupalSSDD_1;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.PracticaGrupalSSDD_1.Bicicletas.Bicycle;
import es.codeurjc.PracticaGrupalSSDD_1.Bicicletas.BicycleRepository;
import es.codeurjc.PracticaGrupalSSDD_1.Bicicletas.BicycleService;
import es.codeurjc.PracticaGrupalSSDD_1.Bicicletas.Bicycle.Estado;

import es.codeurjc.PracticaGrupalSSDD_1.Estaciones.Station;
import es.codeurjc.PracticaGrupalSSDD_1.Estaciones.StationRepository;
import es.codeurjc.PracticaGrupalSSDD_1.Estaciones.StationService;
import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.User;
import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.UserRepository;
import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.User.Estados;


@Controller

public class WebController {
	
	@Autowired
	private UserRepository userRepo; 
	@Autowired
	private StationService stationService;
	@Autowired
	private StationRepository stationRepo;
	@Autowired 
	private BicycleRepository bicycleRepo;

	private User user;
	private Bicycle bicycle;
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
// USUARIOS
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listaUsuarios = userRepo.findAll();
		model.addAttribute("user", listaUsuarios);
		return "/user_templates/users";
	}
	
	@GetMapping("/new_user")
	public String newUser() {
		return "/user_templates/new_user";
	}
	
	@PostMapping("/process_user")
	public String processUser(Model model,
							@RequestParam Long id, 
							@RequestParam String name, 
							@RequestParam String password) {
		user = new User(id, name, password);
		userRepo.save(user);
		model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("password", password);
		return "/user_templates/process_user";
	}
	
	@GetMapping("/modify_user/{id}")
	public String modifyUser(Model model,
							@PathVariable Long id) {
		
		Optional<User> user = userRepo.findById(id);
		String name = user.get().getName();
		String password = user.get().getPassword();
		User.Estados state = user.get().getActive();
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("password", password);
		model.addAttribute("state", state);
		
		return "/user_templates/modify_user";
	}
	
	@PostMapping("/modified_user/{id}")
	public String modifiedUser(Model model,
								@RequestParam String name,
								@RequestParam String password,
								@RequestParam String state,
								@PathVariable Long id) {
		
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			user.get().setName(name);
			user.get().setPassword(password);
			if(state.equals("active")) {
				user.get().setActive(Estados.Activo);
			}
			else {
				user.get().setActive(Estados.Inactivo);
			}
			//Sobreescribimos los datos que haya modificado el usuario
			userRepo.deleteById(id);
			userRepo.save(user.get());
		}
		
		else {
			//Notificar que el usuario no existe
			System.out.println("Usuario no encontrado");
		}
		
		model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("password", password);
		
        return "/user_templates/modified_user";
	}
	
	@GetMapping("/disabled_user/{id}")
	public String disabledUser(Model model,
								@PathVariable Long id) {
		
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			user.get().setActive(Estados.Inactivo);
			
			//Sobreescribimos los datos que haya modificado el usuario
			userRepo.deleteById(id);
			userRepo.save(user.get());
			model.addAttribute("id", id);
			return "/user_templates/disabled_user";
		}
		
		else {
			//Notificar que el usuario no existe
			System.out.println("Usuario no encontrado");
			return "/user_templates/users";
		}
	}
	
	@GetMapping("/show_info/{id}")
	public String showInfo(Model model,
							@PathVariable Long id) {
		
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			String name = user.get().getName();
			String password = user.get().getPassword();
			LocalDate date = user.get().getDate();
			User.Estados state = user.get().getActive();
			
			model.addAttribute("id", id);
			model.addAttribute("name", name);
			model.addAttribute("password", password);
			model.addAttribute("date", date);
			model.addAttribute("state", state);
		}
		return "user_templates/show_info";
	}
	
	

	
	// ESTACIONES
	
		@GetMapping("/stations")
		public String listStation(Model model) {
			List<Station> listaEstaciones = stationRepo.findAll();
			model.addAttribute("station", listaEstaciones);
			return "stations";
		}
		
		@GetMapping("/editStation/{id}")
		public String editStation(Model model,@PathVariable long id) {
			Optional<Station> op = stationService.findOne(id);
			if(op.isPresent()) {
				Station station = op.get();
				model.addAttribute("station",station);
				return "editStation";
			}else {
				return "stations";
					
			}
		}
		
		@GetMapping("/new_station")
		public String newStation() {
			return "new_station";
		}
		
		
		@GetMapping("/station/{id}")
		public String station(Model model,@PathVariable long id) {
			Optional<Station> op = stationService.findOne(id);
			if(op.isPresent()) {
				Station station = op.get();
				model.addAttribute("station",station);
				return "station";
			}else {
				return "stations";
					
			}
		}
		
		@GetMapping("/editedStation/{id}")
		public String updateStation(Model model ,@PathVariable long id, @RequestParam double coords ) {

			/*Optional<Station> s = stationRepo.findById(id);
			if(s.isPresent()) {
				Station stationNueva = s.get();
				stationRepo.deleteById(id);
				stationNueva.setId(id);
				stationNueva.setCoordenadas(coords);
				stationRepo.save(stationNueva);
			}*/
			stationRepo.updateCoordsById(coords, id);

			return "editedStation";
			
		}
	
		
		@PostMapping("/stations")
		public String newStation(Model model, @RequestParam String numSerie, @RequestParam double coords,@RequestParam int capacidad,@RequestParam String state) {
			
			Station s = new Station(numSerie,coords, capacidad);
			if(state.equals("active"))
				s.setEstadoActivo();
			else
				s.setEstadoInactivo();
			stationRepo.save(s);
			model.addAttribute("station",s);
			return "station";
		}
		
		
		@DeleteMapping("/stations")
		public String eliminarEstacion (Model model, @RequestParam Long Id) {
			stationRepo.deleteById(Id);
			return"stations";
		}
		
		@GetMapping("/bicisEstacion/{id}")
		public String bicisEstacion(Model model,@PathVariable long id) {
			Optional<Station> op = stationService.findOne(id);
			if(op.isPresent()) {
				List<Bicycle> bicis = op.get().getBicicletas();
				model.addAttribute("bicicletas",bicis);
				return "bicis_Estacion";
			}else {
				return "stations";
			}
		}
		
		
		@GetMapping("/disableStation/{id}") 
		public String disableStation(Model model,@PathVariable Long id) {
			
			Optional<Station> s = stationRepo.findById(id);
			if(s.isPresent()) {
				/*String name = s.get().getName();
				String password = s.get().getPassword();
				LocalDate date = s.get().getDate();
				User.Estados state = s.get().getActive();*/
				
				/*model.addAttribute("id", id);
				model.addAttribute("name", name);
				model.addAttribute("password", password);
				model.addAttribute("date", date);
				model.addAttribute("state", state);*/
				stationRepo.updateEstadoInactivoById(id);
				for(Bicycle b: s.get().getBicicletas()) {
					bicycleRepo.updateBajaEstadoSinBaseById(b.getId());
				}
			}
			
			return "disableStation";
		}

		
		
		
// BICICLETAS
	@GetMapping("/bicycles")
	public String listBicycle(Model model) {
		List<Bicycle> listaBicicletas = bicycleRepo.findAll();
		model.addAttribute("bicycle", listaBicicletas);
		return "/bicycle_templates/bicycles";
	}
	
	@GetMapping("/new_bicycle")
	public String newBicycle() {
		return "/bicycle_templates/new_bicycle";
	}
	
	@GetMapping("/disabled_bicycle/{id_bicycle}")
	public String disabledBicycle(Model model,@PathVariable Long id_bicycle) {
		
		Optional<Bicycle> bicycle = bicycleRepo.findById(id_bicycle);
		if(bicycle.isPresent()) {
			/*bicycle.get().setEstado(Estado.BAJA);
			
			//Sobreescribimos los datos que haya modificado el usuario
			bicycleRepo.deleteById(id_bicycle);
			bicycleRepo.save(bicycle.get());
			model.addAttribute("id_bicycle", id_bicycle);*/
			bicycle.get().setEstado(Estado.BAJA);
			
			bicycleRepo.updateEstadosById(bicycle.get().getEstados(),id_bicycle);
			bicycleRepo.updateBajaEstadoById(id_bicycle);
			return "/bicycle_templates/disabled_bicycle";
		}
		
		else {
			//Notificar que el usuario no existe
			System.out.println("Bicicleta no encontrada");
			return "/bicycle_templates/bicycles";
		}
	}
	
	@PostMapping("/process_bicycle")
	public String processBicycle(Model model, 
							@RequestParam String n_serie, 
							@RequestParam String modelo) {
		if(n_serie.length()>16) {
			List<Bicycle> listaBicicletas = bicycleRepo.findAll();
			model.addAttribute("bicycle", listaBicicletas);
			return "/bicycle_templates/bicycles";
		}else {
			bicycle = new Bicycle(n_serie, modelo);
			bicycleRepo.save(bicycle);
			model.addAttribute("n_serie", n_serie);
			model.addAttribute("modelo", modelo);
		
			return "/bicycle_templates/process_bicycle";
		}
	}
	
	@GetMapping("/mostrar_info/{id_bicycle}")
	public String mostrarInfo(Model model,@PathVariable Long id_bicycle) {
		
		Optional<Bicycle> bicycle = bicycleRepo.findById(id_bicycle);
		if(bicycle.isPresent()) {
			String nserie = bicycle.get().getNSerie();
			String modelo = bicycle.get().getModelo();
			LocalDate f_alta = bicycle.get().getDate();
			Bicycle.Estado state = bicycle.get().getEstado();
			 
			model.addAttribute("n_serie", nserie);
			model.addAttribute("modelo", modelo);
			model.addAttribute("f_alta", f_alta);
			model.addAttribute("state", state);
			model.addAttribute("estados",bicycle.get().getEstados());
		}
		return "/bicycle_templates/mostrar_info";
	}
	
	@GetMapping("/asignar_estacion/{id_bicycle}")
	public String asignarEstacion(Model model,
							@PathVariable Long id_bicycle) { 
		Optional<Bicycle> bicycle = bicycleRepo.findById(id_bicycle);
		if (bicycle.get().getEstado() == Bicycle.Estado.SIN_BASE) {
			List<Station> listaEstaciones = stationService.findAll();
			model.addAttribute("station", listaEstaciones);
			return "bicycle_templates/asignar_estacion";
		}
		else {
			System.out.println("No se puede asignar una estacion a esta bicicleta");
			return "/bicycles";
		}
	}
	
	@GetMapping("/asigned_station/{id_bicycle}/{Id}")
	public String asignedEstacion(Model model, @PathVariable Long id_bicycle,@PathVariable Long Id) {
		Optional<Station> s = stationRepo.findById(Id);
		Optional<Bicycle> bicycle = bicycleRepo.findById(id_bicycle);
		/*bicycle.get().setEstado(Estado.EN_BASE);
		bicycle.get().setEstacion(s.get());
		
		//Sobreescribimos los datos que haya modificado el usuario
		bicycleRepo.deleteById(id_bicycle);
		Bicycle b = bicycleRepo.save(bicycle.get());  
		s.get().addBicycle(b);
		model.addAttribute("id_bicycle", id_bicycle);*/
		if(s.get().getEstado() == Station.Estado.ACTIVO && s.get().getBicicletas().size() < s.get().getCapacidad()) {
			
			bicycle.get().setEstado(Estado.EN_BASE);
			
			bicycleRepo.updateEstacionById(s.get(), id_bicycle);
			bicycleRepo.updateEstadoById(id_bicycle);
			bicycleRepo.updateEstadosById(bicycle.get().getEstados(),id_bicycle);
			return "/bicycle_templates/asigned_station";
		}else {
			List<Bicycle> listaBicicletas = bicycleRepo.findAll();
			model.addAttribute("bicycle", listaBicicletas);
			return "bicycle_templates/bicycles";
		}
		
		
		
		}
}  
    






















	

	

