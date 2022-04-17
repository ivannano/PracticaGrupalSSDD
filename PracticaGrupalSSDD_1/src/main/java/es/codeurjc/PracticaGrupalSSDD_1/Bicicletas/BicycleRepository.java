package es.codeurjc.PracticaGrupalSSDD_1.Bicicletas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.codeurjc.PracticaGrupalSSDD_1.Bicicletas.Bicycle.Estado;
import es.codeurjc.PracticaGrupalSSDD_1.Estaciones.Station;
@Transactional
public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
	
	@Modifying
	@Query(value = "UPDATE BICYCLE SET ESTADO= :estado.EN_BASE WHERE ID_BICYCLE= :id",nativeQuery = true)
	void updateEstadoById(@Param("estado")Estado e , @Param("id")Long id);
	/*
	@Modifying
	@Query(value = "UPDATE BICYCLE SET ESTACION_ASIG_ID= :s WHERE ID_BICYCLE= :id",nativeQuery = true)
	void updateEstacionById(@Param("s")Station s , @Param("id")Long id);*/
}
