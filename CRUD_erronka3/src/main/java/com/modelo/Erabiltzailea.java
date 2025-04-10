package com.modelo;

public class Erabiltzailea {
	private int id;
	private String email;
	private String pasahitza;
	private int fk_id_rola;
	
	
	public Erabiltzailea() {}
	
	public Erabiltzailea(int id, String email, String pasahitza, int fk_id_rola) {
		super();
		this.id = id;
		this.email = email;
		this.pasahitza = pasahitza;
		this.fk_id_rola = fk_id_rola;
	}

	public Erabiltzailea(String email, String pasahitza) {
		// TODO Auto-generated constructor stub
	}

	public Erabiltzailea(int id, String email, String pasahitza) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
