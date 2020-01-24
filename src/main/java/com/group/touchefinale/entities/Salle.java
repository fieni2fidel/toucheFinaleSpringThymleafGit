package com.group.touchefinale.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Salle implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idsalle;
	
	private String nomsalle;
	
	private Long nombredeplacesalle;
	
	@ManyToOne
	@JoinColumn
	private Lieu lieu;
	
	/*
	 * //@OneToMany
	 * 
	 * @OneToMany(mappedBy="salle") private Collection<Biographie> biographies;
	 */
	
	@OneToMany(mappedBy="salle", cascade = CascadeType.REMOVE)
	private Collection<Evenement> evenements;
	
	/*
	 * @OneToMany(mappedBy="salle") private Collection<Photo>photos;
	 */

	public Salle() {
		super();
		// TODO Auto-generated constructor stub
	}


	/*
	 * public Salle(Long idsalle, String nomsalle, Long nombredeplacesalle, Lieu
	 * lieu, Collection<Biographie> biographies, Collection<Evenement> evenements,
	 * Collection<Photo> photos) { super(); this.idsalle = idsalle; this.nomsalle =
	 * nomsalle; this.nombredeplacesalle = nombredeplacesalle; this.lieu = lieu;
	 * this.biographies = biographies; this.evenements = evenements; this.photos =
	 * photos; }
	 */





	/*
	 * public Salle(Long idsalle, String nomsalle, Long nombredeplacesalle, Lieu
	 * lieu) { super(); this.idsalle = idsalle; this.nomsalle = nomsalle;
	 * this.nombredeplacesalle = nombredeplacesalle; this.lieu = lieu; }
	 */

	/*
	 * public Salle(String nomsalle, Long nombredeplacesalle, Collection<Biographie>
	 * biographies, Collection<Evenement> evenements) { super(); this.nomsalle =
	 * nomsalle; this.nombredeplacesalle = nombredeplacesalle; this.biographies =
	 * biographies; this.evenements = evenements; }
	 */


	public Salle(String nomsalle, Long nombredeplacesalle) {
		super();
		this.nomsalle = nomsalle;
		this.nombredeplacesalle = nombredeplacesalle;
	}

	public Long getIdsalle() {
		return idsalle;
	}

	public void setIdsalle(Long idsalle) {
		this.idsalle = idsalle;
	}

	public String getNomsalle() {
		return nomsalle;
	}

	public void setNomsalle(String nomsalle) {
		this.nomsalle = nomsalle;
	}

	public Long getNombredeplacesalle() {
		return nombredeplacesalle;
	}

	public void setNombredeplacesalle(Long nombredeplacesalle) {
		this.nombredeplacesalle = nombredeplacesalle;
	}

	/*
	 * public Collection<Biographie> getBiographies() { return biographies; }
	 * 
	 * public void setBiographies(Collection<Biographie> biographies) {
	 * this.biographies = biographies; }
	 */

	public Collection<Evenement> getEvenements() {
		return evenements;
	}

	public void setEvenements(Collection<Evenement> evenements) {
		this.evenements = evenements;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}


	/*
	 * public Collection<Photo> getPhotos() { return photos; }
	 * 
	 * 
	 * public void setPhotos(Collection<Photo> photos) { this.photos = photos; }
	 */
	
}
