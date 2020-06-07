package modelo;

import java.util.ArrayList;
import java.util.List;

import daos.ListaDeEsperaDAO;
import views.ListaDeEsperaView;
import views.PacienteView;


public class ItemListaDeEspera {
	
	private int id;
	private Especialidad esp;
	private Paciente paciente;
	private Medico medico;
	
	
	public ItemListaDeEspera(int id, Especialidad esp, Paciente paciente, Medico medico) {
		super();
		this.id = id;
		this.esp = esp;
		this.paciente = paciente;
		this.medico = medico;
	}
	
	
	public ItemListaDeEspera(Especialidad esp, Paciente paciente, Medico medico) {
		super();
		this.esp = esp;
		this.paciente = paciente;
		this.medico = medico;
	}
	
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public int getId() {
		return id;
	}
	public Especialidad getEsp() {
		return esp;
	}
	public void setEsp(Especialidad esp) {
		this.esp = esp;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public ListaDeEsperaView toView() {
		return new ListaDeEsperaView(id, esp.toView(), paciente.toView(), medico.toView());
	}

}
