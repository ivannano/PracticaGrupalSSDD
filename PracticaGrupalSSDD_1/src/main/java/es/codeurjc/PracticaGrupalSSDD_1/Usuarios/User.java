package es.codeurjc.PracticaGrupalSSDD_1.Usuarios;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private Long id;
	private String password;
	private String name;
	private LocalDate date;
	private boolean active;
	
	public User() {
		
	}
	
	public User(Long id, String nombre, String contraseña) {
		this.id = id;
		this.password = contraseña;
		this.name = nombre;
		this.date = LocalDate.now();
		this.active = true;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
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
	
	public void setActive(boolean newActive) {
		this.active = newActive;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User) {
			User user = (User) obj;
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
