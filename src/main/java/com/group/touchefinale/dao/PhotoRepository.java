package com.group.touchefinale.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Lieu;
import com.group.touchefinale.entities.Photo;
import com.group.touchefinale.entities.Salle;


public interface PhotoRepository extends JpaRepository<Photo, Long>{
	
	@Query("select e from Photo e where e.nomphoto like :x")
	public Page<Photo>chercherPhotos(@Param("x")String mc, Pageable pageable);
	
	List<Photo> findAllByArtiste(Artiste artiste);
	//List<Photo> findAllBySalle(Salle salle);
	
	//Requete pour afficher tous les artistes ayant au moins 1 photo 
		// exemple SELECT client, SUM(tarif) FROM achat	GROUP BY client	HAVING SUM(tarif) > 40
		//@Query(value = "SELECT * FROM ARTISTE A, PHOTO B GROUP BY A.idphoto=B.idphoto HAVING COUNT(A.idphoto) >= 1", nativeQuery = true)
	/*
	 * @Query(value = "SELECT count(idphoto), nomphoto, artiste_idartiste " +
	 * "FROM db_groupe_toochefinale.photo group by artiste_idartiste having count(idphoto)>1"
	 * , nativeQuery = true)
	 */
		//@Query("select e FROM Photo e GROUP BY e.artiste.idartiste HAVING COUNT (e.idphoto)=null")
		//public List<Photo>listeArtisteAvecAuMoinsUnePhoto();
		
	/*
	 * SELECT count(idphoto), nomphoto, artiste_idartiste FROM
	 * db_groupe_toochefinale.photo group by artiste_idartiste having
	 * count(idphoto)>1;
	 */

}
