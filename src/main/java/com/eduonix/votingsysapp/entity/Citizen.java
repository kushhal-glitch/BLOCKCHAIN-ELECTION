package com.eduonix.votingsysapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="citizens")
@Entity
public class Citizen {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="aadharid", nullable=false, unique=true, length=12)
	private String aadharid;
	
	@Column(name="email", nullable=false, unique=true, length=45)
	private String email;
	
	@Column(name="password", nullable=false, length=64)
	private String password;
	
	@Column(name="hasvoted", nullable=false)
	private boolean hasvoted;
	
	@Column(name="privatekey", unique=true, nullable=false)
	private String privatekey;

	public Long getId() {
		return id;
	}
	
	public void setName(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAadharid() {
		return aadharid;
	}

	public void setAadharid(String aadharid) {
		this.aadharid = aadharid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getHasvoted() {
		return hasvoted;
	}

	public void setHasvoted(boolean hasvoted) {
		this.hasvoted = hasvoted;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}

	public Citizen(Long id, String name, String aadharid, String email, String password, boolean hasvoted,
			String privatekey) {
		super();
		this.id = id;
		this.name = name;
		this.aadharid = aadharid;
		this.email = email;
		this.password = password;
		this.hasvoted = hasvoted;
		this.privatekey = privatekey;
	}

	public Citizen() {
		super();
	}
	
}
