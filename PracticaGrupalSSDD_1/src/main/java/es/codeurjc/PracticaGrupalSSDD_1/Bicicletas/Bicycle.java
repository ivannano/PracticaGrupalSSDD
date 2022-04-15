package es.codeurjc.PracticaGrupalSSDD_1.Bicicletas;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bicycle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id = null;
	
	private String n_serie;
	private String modelo;
	private LocalDate f_alta;
	private Estado estado;
	private ArrayList<Estado> estados = new ArrayList<Estado>();
	
	public enum Estado {SIN_BASE, EN_BASE, RESERVADA, BAJA}
	
	public Bicycle() {
		
	}
	public Bicycle(String ns, String m) {
		n_serie = ns;
		modelo = m;
		f_alta = LocalDate.now();
		estado = Estado.SIN_BASE;
		estados.add(Estado.SIN_BASE);
	}
	
	public String getNSerie() {
		return this.n_serie;
	}
	public String getModelo() {
		return this.modelo;
	}
	public LocalDate getDate() {
		return this.f_alta;
	}
	public Estado getEstado() {
		return this.estado;
	}
	public Long getId() {
		return this.Id;
	}
	public ArrayList<Estado> getEstados(){
		return estados;
	}
	
	public void setEstado(Estado e){
		this.estado = e;
		estados.add(e);
	}
	
	
	@Override
	public String toString() {
		String s = "Bicicleta [Numero de serie: " + this.n_serie + "\nModelo: " + this.modelo + "\nFecha de alta: " + this.f_alta + "\nEstado: ";
		if (estado == Estado.SIN_BASE) {
			s = s + "sin base. Esta dada de alta pero no tiene estacion asignada.";
		}
		else if (estado == Estado.EN_BASE) {
			s = s + "en base. La bicileta está asociada a una estacion.";
		}
		else if (estado == Estado.RESERVADA) {
			s = s + "reservada. Un usuario esta haciendo uso de la bicicleta.";
		}
		else {
			s = s + "baja. No esta operativa";
		}
		return s;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)return true;
		if(o == null)return false;
		if(getClass() != o.getClass())return false;
		
		Bicycle b = (Bicycle) o;
		
		return (this.getNSerie().equals(b.getNSerie()));
	}
}
