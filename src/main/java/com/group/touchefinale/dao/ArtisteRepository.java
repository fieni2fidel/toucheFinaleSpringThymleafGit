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
	public Page<Artiste>chercherArtistes(@Param("x")String mc, Pageable pageable);	
	
	@Query("select e from Artiste e where e.nomcompletartiste like :x")
	public List<Artiste>chercherArtisteFrontEnd(@Param("x")String mc);
	
	@Query("select e from Artiste e where e.origineartiste = :x")
	public List<Artiste>chercherArtisteNationalite(@Param("x")String nat);
	
	public List<Artiste>findByNomcompletartisteContaining(String mc);
	
	public List<Artiste>findByNomcompletartisteLike(String mc);
	
	//public List<Artiste>findAllOrderByNomcompletartiste();

	@Query("select e from Artiste e where e.datenaissanceartiste > :x and e.datenaissanceartiste < :y")
	public List<Artiste>chercherArtiste(@Param("x")Date d1, @Param("y")Date d2);
	
	@Query(value = "Select idartiste from artiste order by evenements desc", nativeQuery = true)
	public List<Artiste>trierEvenementParId(Long id);
	
	@Query(value = "Select b From Biographie b where artiste.idartiste = :idartiste ")
	public List<Biographie>findBiographieByIdArtist(Long idartiste);
	
	public List<Artiste>findByNomcompletartisteStartingWith(String debutnom);
	
	
	@Query("select e from Artiste e where e.nomcompletartiste like :x")
	public List<Artiste>rechercherArtistes(@Param("x")String mc);	
	
	
	//Requete pour afficher tous les artistes ayant au moins 1 photo 
	// exemple SELECT client, SUM(tarif) FROM achat	GROUP BY client	HAVING SUM(tarif) > 40
	/*
	 * @Query(value =
	 * "SELECT * FROM ARTISTE A, PHOTO B GROUP BY A.idphoto=B.idphoto HAVING COUNT(A.idphoto) >= 1"
	 * , nativeQuery = true)
	 * 
	 * @Query("select e from Artiste e, Photo b GROUP BY e.idphoto=b.idphoto HAVING COUNT(e.photos)> 1"
	 * ) public List<Artiste>listeArtisteAvecAuMoinsUnePhoto();
	 */
	
	
	public List<Artiste>findByPhotosGreaterThanEqual(Long x);

}
