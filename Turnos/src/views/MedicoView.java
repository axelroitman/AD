package views;

import java.util.List;
import java.util.Date;

import modelo.Especialidad;

public class MedicoView extends UsuarioView {
	public MedicoView(int idUsr,String usr, String pass, String nombre, String telefono, String dni, Date fechaNac,
			String matricula, List<EspecialidadView> especialidades) {
    	
		super(idUsr, usr, pass, nombre, telefono, dni, fechaNac);
		this.matricula = matricula;
		this.especialidades = especialidades;
	}
	
	private String matricula;
    private List<EspecialidadView> especialidades;
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public List<EspecialidadView> getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(List<EspecialidadView> especialidades) {
		this.especialidades = especialidades;
	}
	
	
}
