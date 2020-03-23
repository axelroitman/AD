package views;

import java.util.Date;

public class UsuarioView {
	 
		public UsuarioView(int id, String usr, String pass, String nombre, String telefono, int dni, Date fechaNac) {
				this.id = id;
		    	this.usr = usr;
				this.pass = pass;
				this.nombre = nombre;
				this.telefono = telefono;
				this.dni = dni;
				this.fechaNac = fechaNac;
			}
		    
	    private int id;
		private String usr;
		private String pass;
		private String nombre;
		private String telefono;
		private int dni;
		private Date fechaNac;
	    
	    
		public int getId() {
			return id;
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
		public int getDni() {
			return dni;
		}
		public void setDni(int dni) {
			this.dni = dni;
		}
		public Date getFechaNac() {
			return fechaNac;
		}
		public void setFechaNac(Date fechaNac) {
			this.fechaNac = fechaNac;
		}
}
