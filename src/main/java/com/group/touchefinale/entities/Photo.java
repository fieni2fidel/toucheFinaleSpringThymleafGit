package com.group.touchefinale.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Photo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idphoto;
	
	private String nomphoto;
	
	private String urlphoto;
	
	@ManyToOne()
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn
	private Artiste artiste;

	/*
	 * @ManyToOne()
	 * 
	 * @NotFound(action = NotFoundAction.IGNORE)
	 * 
	 * @JoinColumn private Salle salle;
	 */
	
	
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	public Photo(Long idphoto, String nomphoto, String urlphoto, Artiste artiste) {
		super();
		this.idphoto = idphoto;
		this.nomphoto = nomphoto;
		this.urlphoto = urlphoto;
		this.artiste = artiste;
	}




	public Long getIdphoto() {
		return idphoto;
	}

	public void setIdphoto(Long idphoto) {
		this.idphoto = idphoto;
	}

	public String getNomphoto() {
		return nomphoto;
	}

	public void setNomphoto(String nomphoto) {
		this.nomphoto = nomphoto;
	}

	public String getUrlphoto() {
		return urlphoto;
	}

	public void setUrlphoto(String urlphoto) {
		this.urlphoto = urlphoto;
	}

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	/*
	 * public Salle getSalle() { return salle; }
	 * 
	 * public void setSalle(Salle salle) { this.salle = salle; }
	 */
	
	
	
}
