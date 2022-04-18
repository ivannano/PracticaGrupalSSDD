package es.codeurjc.PracticaGrupalSSDD_1.Estaciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StationRepository extends JpaRepository<Station, Long>{
	
	@Modifying
	@Query(value = "UPDATE STATION SET COORDENADAS= :coords WHERE STATION.ID= :id",nativeQuery = true)
	void updateCoordsById(@Param("coords") double coords, @Param("id")Long id);
	
	@Modifying
	@Query(value = "UPDATE STATION SET ESTADO= 1 WHERE STATION.ID= :id",nativeQuery = true)
	void updateEstadoInactivoById(@Param("id")Long id);
	
	
	
}
