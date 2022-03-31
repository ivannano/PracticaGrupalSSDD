package es.codeurjc.PracticaGrupalSSDD_1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.User;
import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepo; 
	
	@GetMapping("/users")
	public String users(Model model) {
		List<User> listaUsuarios = userRepo.findAll();
		model.addAttribute("user", listaUsuarios);
		return "users";
	}
	
}
