package com.modelo;
/*
 * Create a User model From database;
 */
public class Usuario {
	private int id;
	private String nombre;
	private String email;
	private String password;
	
	
	/*Eriakitzaileak*/
	public Usuario() {}

	public Usuario(int id, String nombre, String email, String password) {
		
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password=password;
	
	}

	public Usuario(String nombre, String email, String password) {
		this.nombre = nombre;
		this.email = email;
		this.password=password;
	}
	
	/*GETTERS & SETTERS*/
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}

