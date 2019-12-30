package com.group.touchefinale.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Biographie;
import com.group.touchefinale.entities.Evenement;

public interface ArtisteRepository extends JpaRepository<Artiste, Long>{

	List<Artiste> findAllByOrderByNomcompletartiste();
	
	public List<Artiste>findAllByOrderByIdartisteDesc();
	
	@Query("select e from Artiste e where e.nomcompletartiste like :x")
	public Page<Artiste>chercherEtudiants(@Param("x")String mc, Pageable pageable);	
	
	@Query("select e from Artiste e where e.nomcompletartiste like :x")
	public List<Artiste>chercherArtisteFrontEnd(@Param("x")String mc);
	
	public List<Artiste>findByNomcompletartisteContaining(String mc);
	
	public List<Artiste>findByNomcompletartisteLike(String mc);

	@Query("select e from Artiste e where e.datenaissanceartiste > :x and e.datenaissanceartiste < :y")
	public List<Artiste>chercherArtiste(@Param("x")Date d1, @Param("y")Date d2);
	
	@Query(value = "Select idartiste from artiste order by evenements desc", nativeQuery = true)
	public List<Artiste>trierEvenementParId(Long id);
	
	@Query(value = "Select b From Biographie b where artiste.idartiste = :idartiste ")
	public List<Biographie>findBiographieByIdArtist(Long idartiste);
	
	public List<Artiste>findByNomcompletartisteStartingWith(String debutnom);

	
	/*
	 * @Query(value =
	 * "Select e FROM Artiste e WHERE e.idartiste= :a AND e.evenements.datedebutevenement >= :x"
	 * ) public List<Artiste>dateArtistesAVenir(@Param("a")Long a, @Param("x")Date
	 * x);
	 */
	 
}
