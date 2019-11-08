package com.group.touchefinale.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tarif implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idtarif;
	
	private long montanttarif;
	
	private String devisetarif;
	
	private String typetarif;

	public Tarif() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tarif(long idtarif, long montanttarif, String devisetarif, String typetarif) {
		super();
		this.idtarif = idtarif;
		this.montanttarif = montanttarif;
		this.devisetarif = devisetarif;
		this.typetarif = typetarif;
	}

	public Tarif(long montanttarif, String devisetarif, String typetarif) {
		super();
		this.montanttarif = montanttarif;
		this.devisetarif = devisetarif;
		this.typetarif = typetarif;
	}

	public long getIdtarif() {
		return idtarif;
	}

	public void setIdtarif(long idtarif) {
		this.idtarif = idtarif;
	}

	public long getMontanttarif() {
		return montanttarif;
	}

	public void setMontanttarif(long montanttarif) {
		this.montanttarif = montanttarif;
	}

	public String getDevisetarif() {
		return devisetarif;
	}

	public void setDevisetarif(String devisetarif) {
		this.devisetarif = devisetarif;
	}

	public String getTypetarif() {
		return typetarif;
	}

	public void setTypetarif(String typetarif) {
		this.typetarif = typetarif;
	}
	
	
	
}
