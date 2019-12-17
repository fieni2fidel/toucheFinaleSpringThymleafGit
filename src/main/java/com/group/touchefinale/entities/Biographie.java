package com.group.touchefinale.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
public class Biographie implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idbiographie;
	
	private String nombiographie;
	
	@Column(length=1000)
	private String descriptionbiographie;
	
	@ManyToOne
	//@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn
	private Artiste artiste;
	
	@ManyToOne
	//@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn
	private Salle salle;
	
	

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	
	

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Biographie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Biographie(Long idbiographie, String nombiographie, String descriptionbiographie) {
		super();
		this.idbiographie = idbiographie;
		this.nombiographie = nombiographie;
		this.descriptionbiographie = descriptionbiographie;
	}
	

	public Biographie(String nombiographie, String descriptionbiographie, Artiste artiste) {
		super();
		this.nombiographie = nombiographie;
		this.descriptionbiographie = descriptionbiographie;
		this.artiste = artiste;
	}
	

	public Biographie(Long idbiographie, String nombiographie, String descriptionbiographie, Salle salle) {
		super();
		this.idbiographie = idbiographie;
		this.nombiographie = nombiographie;
		this.descriptionbiographie = descriptionbiographie;
		this.salle = salle;
	}
	

	public Biographie(Long idbiographie, String nombiographie, String descriptionbiographie, Artiste artiste,
			Salle salle) {
		super();
		this.idbiographie = idbiographie;
		this.nombiographie = nombiographie;
		this.descriptionbiographie = descriptionbiographie;
		this.artiste = artiste;
		this.salle = salle;
	}

	public Biographie(Long idbiographie, String nombiographie, String descriptionbiographie, Artiste artiste) {
		super();
		this.idbiographie = idbiographie;
		this.nombiographie = nombiographie;
		this.descriptionbiographie = descriptionbiographie;
		this.artiste = artiste;
	}

	public Biographie(String nombiographie, String descriptionbiographie) {
		super();
		this.nombiographie = nombiographie;
		this.descriptionbiographie = descriptionbiographie;
	}

	public Long getIdbiographie() {
		return idbiographie;
	}

	public void setIdbiographie(Long idbiographie) {
		this.idbiographie = idbiographie;
	}

	public String getNombiographie() {
		return nombiographie;
	}

	public void setNombiographie(String nombiographie) {
		this.nombiographie = nombiographie;
	}

	public String getDescriptionbiographie() {
		return descriptionbiographie;
	}

	public void setDescriptionbiographie(String descriptionbiographie) {
		this.descriptionbiographie = descriptionbiographie;
	}
	
	

}
