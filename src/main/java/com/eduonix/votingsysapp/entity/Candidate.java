package com.eduonix.votingsysapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="candidates")
@Entity
public class Candidate {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="numberofvotes")
	private int numberofvotes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberofvotes() {
		return numberofvotes;
	}

	public void setNumberofvotes(int numberofvotes) {
		this.numberofvotes = numberofvotes;
	}

	public Candidate(int id, String name, int numberofvotes) {
		super();
		this.id = id;
		this.name = name;
		this.numberofvotes = numberofvotes;
	}

	public Candidate() {
		super();
	}
	
}
