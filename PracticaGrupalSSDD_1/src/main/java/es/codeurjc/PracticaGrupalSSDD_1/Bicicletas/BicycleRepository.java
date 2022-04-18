package es.codeurjc.PracticaGrupalSSDD_1.Bicicletas;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.codeurjc.PracticaGrupalSSDD_1.Estaciones.Station;
@Transactional
public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
	
	//Pone la bicicleta con ese id en EN_BASE
	@Modifying
	@Query(value = "UPDATE BICYCLE SET ESTADO= 1  WHERE ID_BICYCLE= :id",nativeQuery = true)
	void updateEstadoEnBaseById(@Param("id")Long id);
	
	
	//Pone todos los estados por los que ha pasado la bicicleta
	@Modifying
	@Query(value = "UPDATE BICYCLE SET ESTADOS= :estado WHERE ID_BICYCLE= :id",nativeQuery = true)
	void updateEstadosById(@Param("estado") String estado, @Param("id")Long id);
	
	
	//Pone la bicicleta con ese id asignada a una estacion
	@Modifying
	@Query(value = "UPDATE BICYCLE SET ESTACION_ASIG_ID= :s WHERE ID_BICYCLE= :id",nativeQuery = true)
	void updateEstacionById(@Param("s")Station s , @Param("id")Long id);
	
	
	//Pone la bicicleta con ese id en BAJA
	@Modifying 
	@Query(value = "UPDATE BICYCLE SET ESTADO= 3  WHERE ID_BICYCLE= :id",nativeQuery = true)
	void updateBajaEstadoById(@Param("id") Long id);
	
	
	//Pone la bicicleta con ese id en SIN_BASE
	@Modifying 
	@Query(value = "UPDATE BICYCLE SET ESTADO= 0  WHERE ID_BICYCLE= :id",nativeQuery = true)
	void updateBajaEstadoSinBaseById(@Param("id") Long id);
	
}
