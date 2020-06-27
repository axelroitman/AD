package modelo;
import java.util.*;

import views.UsuarioView;

public class Usuario {

	private int idUsr;
	private String usr;
	private String pass;
	private String nombre;
	private String telefono;
	private String dni;
	private Date fechaNac;
	
	
    public Usuario(int idUsr, String usr, String pass, String nombre, String telefono, String dni, Date fechaNac) {
    	this.idUsr = idUsr;
    	this.usr = usr;
		this.pass = pass;
		this.nombre = nombre;
		this.telefono = telefono;
		this.dni = dni;
		this.fechaNac = fechaNac;
	}
    
   
    
	public int getIdUsr() {
		return idUsr;
	}
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public UsuarioView toView() {
		
		return new UsuarioView(idUsr, usr, pass, nombre, telefono, dni, fechaNac);
	}

}