package modelo;
import java.util.*;

import views.EspecialidadView;
import views.MedicoView;


//Faltan metodos (16/03)

public class Medico extends Usuario {

	public Medico(int idUsr, String usr, String pass, String nombre, String telefono, String dni, Date fechaNac,
			String matricula) {
		
		super(idUsr, usr, pass, nombre, telefono, dni, fechaNac);
		this.matricula = matricula;
		//this.especialidades = especialidades;
		//this.agenda = agenda;
	}
	
	private String matricula;
    //private Usuario usuario;
	
	private Collection<Especialidad> especialidades;
    
    
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/*public Collection<Especialidad> getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(Collection<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}*/
	public MedicoView toView() {
		
		List<EspecialidadView> esp = new ArrayList<EspecialidadView>();
		for(Especialidad e : especialidades) 
		{
			 esp.add(e.toView());
		}

		return new MedicoView(super.getIdUsr(), super.getUsr(), super.getPass(), super.getNombre(), super.getTelefono(), super.getDni(), super.getFechaNac(), matricula, esp);
	}

}