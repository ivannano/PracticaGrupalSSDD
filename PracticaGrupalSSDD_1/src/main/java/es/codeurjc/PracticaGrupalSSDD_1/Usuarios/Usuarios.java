package es.codeurjc.PracticaGrupalSSDD_1.Usuarios;

import java.awt.Graphics;
import java.util.ArrayList;

public class Usuarios {
	ArrayList<User> usuarios;
	
	public Usuarios() {
		this.usuarios = new ArrayList<>();
	}
	
	public boolean newUser(Long id, String nombre, String contraseña, Graphics foto) {
		User user = new User(id, nombre, contraseña, foto);
		if(!exist(user)) {
			usuarios.add(user);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean exist(User user) {
		if(usuarios.contains(user)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void userList() {
		
	}
}
