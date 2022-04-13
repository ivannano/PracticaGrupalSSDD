package es.codeurjc.PracticaGrupalSSDD_1.Bicicletas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
	
}
