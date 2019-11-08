package com.group.touchefinale.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Lieu implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idlieu;
	
	private String continentlieu;
	
	private String regionlieu;
	
	private String payslieu;
	
	private String villelieu;

	@OneToMany
	
	private Collection<Salle> salles;
	

	public Lieu() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Lieu(Long idlieu) {
		super();
		this.idlieu = idlieu;
	}


	public Lieu(Long idlieu, String continentlieu, String regionlieu, String payslieu, String villelieu,
			 Collection<Salle> salles) {
		super();
		this.idlieu = idlieu;
		this.continentlieu = continentlieu;
		this.regionlieu = regionlieu;
		this.payslieu = payslieu;
		this.villelieu = villelieu;
		
		this.salles = salles;
	}


	public Lieu(String continentlieu, String regionlieu, String payslieu, String villelieu,
			Collection<Salle> salles) {
		super();
		this.continentlieu = continentlieu;
		this.regionlieu = regionlieu;
		this.payslieu = payslieu;
		this.villelieu = villelieu;
		
		this.salles = salles;
	}


	public Long getIdlieu() {
		return idlieu;
	}


	public void setIdlieu(Long idlieu) {
		this.idlieu = idlieu;
	}



	public String getContinentlieu() {
		return continentlieu;
	}


	public void setContinentlieu(String continentlieu) {
		this.continentlieu = continentlieu;
	}


	public String getRegionlieu() {
		return regionlieu;
	}


	public void setRegionlieu(String regionlieu) {
		this.regionlieu = regionlieu;
	}


	public String getPayslieu() {
		return payslieu;
	}


	public void setPayslieu(String payslieu) {
		this.payslieu = payslieu;
	}


	public String getVillelieu() {
		return villelieu;
	}


	public void setVillelieu(String villelieu) {
		this.villelieu = villelieu;
	}


	public Collection<Salle> getSalles() {
		return salles;
	}


	public void setSalles(Collection<Salle> salles) {
		this.salles = salles;
	}


}
