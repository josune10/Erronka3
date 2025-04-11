package com.modelo;

public class Erabiltzailea {
	private int id_erabiltzaile;
	private String email;
	private String pasahitza;
	private int fk_id_rola;
	
	
	public Erabiltzailea() {}
	
	public Erabiltzailea(int id_erabiltzaile, String email, String pasahitza, int fk_id_rola) {
		super();
		this.id_erabiltzaile = id_erabiltzaile;
		this.email = email;
		this.pasahitza = pasahitza;
		this.fk_id_rola = fk_id_rola;
	}

	public Erabiltzailea(String email, String pasahitza) {
		// TODO Auto-generated constructor stub
	}

	public Erabiltzailea(int id_erabiltzaile, String email, String pasahitza) {
		// TODO Auto-generated constructor stub
	}

	public int getId_erabiltzaile() {
		return id_erabiltzaile;
	}

	public void setId_erabiltzaile(int id_erabiltzaile) {
		this.id_erabiltzaile = id_erabiltzaile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public int getFk_id_rola() {
		return fk_id_rola;
	}

	public void setFk_id_rola(int fk_id_rola) {
		this.fk_id_rola = fk_id_rola;
	}
	
	
	
	
}
