package views;

import java.util.Date;

public class UsuarioView {
	 
		public UsuarioView(int idUsr, String usr, String pass, String nombre, String telefono, String dni, Date fechaNac) {
		    	this.usr = usr;
				this.pass = pass;
				this.nombre = nombre;
				this.telefono = telefono;
				this.dni = dni;
				this.fechaNac = fechaNac;
				this.idUsr = idUsr;
			}
		
		private int idUsr;
		private String usr;
		private String pass;
		private String nombre;
		private String telefono;
		private String dni;
		private Date fechaNac;
	    
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
}
