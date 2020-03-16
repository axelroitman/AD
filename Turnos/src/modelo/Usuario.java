package modelo;
import java.util.*;

//Faltan métodos (16/03)

public class Usuario {


    public Usuario(String usr, String pass, String nombre, String telefono, String dni, Date fechaNac) {
		this.usr = usr;
		this.pass = pass;
		this.nombre = nombre;
		this.telefono = telefono;
		this.dni = dni;
		this.fechaNac = fechaNac;
	}
    
	protected String usr;
    protected String pass;
    protected String nombre;
    protected String telefono;
    protected String dni;
    protected Date fechaNac;
    
    
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

    
}