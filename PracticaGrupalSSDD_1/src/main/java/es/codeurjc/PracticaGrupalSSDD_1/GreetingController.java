package es.codeurjc.PracticaGrupalSSDD_1;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.User;
import es.codeurjc.PracticaGrupalSSDD_1.Usuarios.UserService;

@Controller
public class GreetingController {
	
	@GetMapping("/greeting")
	public String greeting(Model model) {
		model.addAttribute("name", "BiciURJC");
		return "greeting_template";
	}
	
}
