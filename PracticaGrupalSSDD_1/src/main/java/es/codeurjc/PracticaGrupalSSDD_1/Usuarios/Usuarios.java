package es.codeurjc.PracticaGrupalSSDD_1.Usuarios;

import java.awt.Graphics;
import java.util.ArrayList;

public class Usuarios {
	ArrayList<Usuario> usuarios;
	
	public Usuarios() {
		this.usuarios = new ArrayList<>();
	}
	
	public boolean newUser(String id, String nombre, String contraseña, Graphics foto) {
		Usuario user = new Usuario(id, nombre, contraseña, foto);
		if(!exist(user)) {
			usuarios.add(user);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean exist(Usuario user) {
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
