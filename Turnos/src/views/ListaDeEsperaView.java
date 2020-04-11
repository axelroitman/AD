package views;

import java.util.Collection;
import java.util.List;

public class ListaDeEsperaView {
	private int id;
	private EspecialidadView esp;
	private List<PacienteView> pacientes;
	private MedicoView medico;
	
	public ListaDeEsperaView(int id, EspecialidadView esp, List<PacienteView> pacientes, MedicoView medico) {
		super();
		this.id = id;
		this.esp = esp;
		this.pacientes = pacientes;
		this.medico = medico;
	}
	
	
	public int getId() {
		return id;
	}
	public EspecialidadView getEsp() {
		return esp;
	}
	public void setEsp(EspecialidadView esp) {
		this.esp = esp;
	}
	public List<PacienteView> getPacientes() {
		return pacientes;
	}
	public void setPacientes(List<PacienteView> pacientes) {
		this.pacientes = pacientes;
	}
	public MedicoView getMedico() {
		return medico;
	}
	public void setMedico(MedicoView medico) {
		this.medico = medico;
	}
	
	
}
