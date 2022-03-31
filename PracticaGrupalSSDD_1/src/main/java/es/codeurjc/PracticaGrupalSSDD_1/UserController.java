package es.codeurjc.PracticaGrupalSSDD_1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.User;
import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.UserRepository;

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
	
	@PostMapping("/processUser")
	public String processUser(@RequestParam Long id, 
							@RequestParam String nombre, 
							@RequestParam String contraseña) {
		user.setId(id);
		user.setName(nombre);
		user.setPassword(contraseña);
		userRepo.save(user);
		return "processUser";
	}
	
	
	
}
