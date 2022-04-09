package es.codeurjc.PracticaGrupalSSDD_1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.User;
import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.UserRepository;
import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.User.Estados;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepo; 
	
	private User user;
	
	@GetMapping("/user_index")
	public String users(Model model) {
		
		return "user_index";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listaUsuarios = userRepo.findAll();
		model.addAttribute("user", listaUsuarios);
		return "users";
	}
	
	@GetMapping("/new_user")
	public String newUser() {

		return "new_user";
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
		return "process_user";
	}
	
	@GetMapping("/modify_user")
	public String modifyUser() {

		return "modify_user";
	}
	
	@PostMapping("/modified_user")
	public String modifiedUser(Model model,
								@RequestParam String name,
								@RequestParam String password,
								@RequestParam String state,
								@RequestParam Long id) {
		
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
		
        return "modified_user";
	}
	
	@PostMapping("/disabled_user")
	public String disabledUser(Model model,
								@RequestParam Long id) {
		
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			user.get().setActive(Estados.Inactivo);
			
			//Sobreescribimos los datos que haya modificado el usuario
			userRepo.deleteById(id);
			userRepo.save(user.get());
		}
		
		else {
			//Notificar que el usuario no existe
			System.out.println("Usuario no encontrado");
		}
		
		model.addAttribute("id", id);
		return "disabled_user";
	}
	
}
