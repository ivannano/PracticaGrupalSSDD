package es.codeurjc.PracticaGrupalSSDD_1.Estaciones;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import es.codeurjc.PracticaGrupalSSDD_1.Bicicletas.Bicycle;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class Station {
	//ATRIBUTOS
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id=null;
	private String numSerie;
	private double coordenadas;
	private String fechaInstalacion;
	private Estado estado;
	private int capacidad;
	
	@OneToMany(mappedBy="estacionAsig")
	private List<Bicycle> bicicletas;
	
	public enum Estado{
		ACTIVO,INACTIVO
	}
	
	public Station () {
	}
	
	public Station(String numSerie, double coordenadas, int capacidad) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		this.capacidad = capacidad;
		this.coordenadas = coordenadas;
		this.numSerie = numSerie;
		this.fechaInstalacion = dtf.format(LocalDateTime.now());
		this.estado = Estado.ACTIVO;
	}
	
	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public double getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(double coordenadas) {
		this.coordenadas = coordenadas;
	}

	public String getFechaInstalacion() {
		return fechaInstalacion;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public void setEstadoActivo() {
		this.estado = Estado.ACTIVO;
	}
	
	public void setEstadoInactivo() {
		this.estado = Estado.INACTIVO;
	}
	
	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public Long getId() {
		return Id;
	}
	public void setId(long id) {
		this.Id = id; 
	}
	
	public void addBicycle(Bicycle b) {
		this.bicicletas.add(b);
	}
	public List<Bicycle> getBicicletas(){
		return this.bicicletas;
	}

	@Override
	public String toString() {
		return "Estacion [numSerie=" + numSerie + ", coordenadas=" + coordenadas + ", fechaInstalacion="
				+ fechaInstalacion + ", estado=" + estado + ", capacidad=" + capacidad + "]";
	}
}
