package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name= "usuarios")

public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String usr;
	private String pass;
	private String nombre;
	private String telefono;
	private String dni;
	@Temporal(TemporalType.DATE)
	private Date fechaNac;
	
	
	public UsuarioEntity(int id, String usr, String pass, String nombre, String telefono, String dni, Date fechaNac) {
		super();
		this.id = id;
		this.usr = usr;
		this.pass = pass;
		this.nombre = nombre;
		this.telefono = telefono;
		this.dni = dni;
		this.fechaNac = fechaNac;
	}
	
	public UsuarioEntity() {}
	
	
	public int getIdUsr() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
}
