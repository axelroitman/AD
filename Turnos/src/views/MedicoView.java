package views;

import java.util.Collection;
import java.util.Date;

import modelo.Especialidad;

public class MedicoView extends UsuarioView {
	public MedicoView(int idUsr,String usr, String pass, String nombre, String telefono, String dni, Date fechaNac,
			String matricula, Collection<Especialidad> especialidades) {
    	
		super(idUsr, usr, pass, nombre, telefono, dni, fechaNac);
		this.matricula = matricula;
		this.especialidades = especialidades;
	}
	
	private String matricula;
    private Collection<Especialidad> especialidades;
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Collection<Especialidad> getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(Collection<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
	
	
}
