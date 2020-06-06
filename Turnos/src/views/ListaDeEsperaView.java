package views;

import java.util.Collection;
import java.util.List;

public class ListaDeEsperaView {
	private int id;
	private EspecialidadView esp;
	private PacienteView paciente;
	private MedicoView medico;
	
	public ListaDeEsperaView(int id, EspecialidadView esp, PacienteView paciente, MedicoView medico) {
		super();
		this.id = id;
		this.esp = esp;
		this.paciente = paciente;
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
	public PacienteView getPaciente() {
		return paciente;
	}
	public void setPacientes(PacienteView paciente) {
		this.paciente = paciente;
	}
	public MedicoView getMedico() {
		return medico;
	}
	public void setMedico(MedicoView medico) {
		this.medico = medico;
	}
	
	
}
