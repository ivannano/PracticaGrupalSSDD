package es.codeurjc.PracticaGrupalSSDD_1;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.PracticaGrupalSSDD_1.Bicicletas.Bicycle;
import es.codeurjc.PracticaGrupalSSDD_1.Bicicletas.BicycleRepository;
import es.codeurjc.PracticaGrupalSSDD_1.Bicicletas.BicycleService;


import es.codeurjc.PracticaGrupalSSDD_1.Estaciones.Station;
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
	private BicycleRepository bicycleRepo;

	private User user;

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
		List<Station> listaEstaciones = stationService.findAll();
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
	
// BICICLETAS
	@GetMapping("/bicycles")
	public String listBicycle(Model model) {
		List<Bicycle> listaBicicletas = bicycleRepo.findAll();
		model.addAttribute("bicycle", listaBicicletas);
		return "/bicycle_templates/bicycles";
	}
}
