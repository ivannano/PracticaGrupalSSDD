package es.codeurjc.Usuarios;

import java.awt.Graphics;
import java.time.LocalDate;

public class Usuario {
	private String id;
	private String password;
	private String name;
	private Graphics picture;
	private LocalDate date;
	private boolean active;
	
	public Usuario(String id, String nombre, String contraseña, Graphics foto) {
		this.id = id;
		this.password = contraseña;
		this.name = nombre;
		this.picture = foto;
		this.date = LocalDate.now();
		this.active = true;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Graphics getImage() {
		return this.picture;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
	
	public void setPicture(Graphics newPicture) {
		this.picture = newPicture;
	}
	
	public void setActive(boolean newActive) {
		this.active = newActive;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Usuario) {
			Usuario user = (Usuario) obj;
			if(this.getId().equals(user.getId())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		String s = "Nombre: " + this.name + ", Identificador: " + this.id + ", Estado: ";
		if(this.active) {
			s = s + "activo";
		}
		else {
			s = s + "inactivo";
		}
		return s;
	}
	
}
