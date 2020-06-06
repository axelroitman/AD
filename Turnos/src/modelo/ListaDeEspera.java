package modelo;

import java.util.ArrayList;
import java.util.List;

import daos.ListaDeEsperaDAO;
import views.ListaDeEsperaView;
import views.PacienteView;


public class ListaDeEspera {
	
	private int id;
	private Especialidad esp;
	private List<Paciente> pacientes;
	private Medico medico;
	
	
	public ListaDeEspera(int id, Especialidad esp, List<Paciente> pacientes, Medico medico) {
		super();
		this.id = id;
		this.esp = esp;
		this.pacientes = pacientes;
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
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	public ListaDeEsperaView toView() {
		List<PacienteView> pac = new ArrayList<PacienteView>();
		for(Paciente p : pacientes) 
		{
			pac.add(p.toView());
		}

		return new ListaDeEsperaView(id, esp.toView(), pac, medico.toView());
	}


	public void agregarALista(Paciente p) {
		this.pacientes.add(p);
		ListaDeEsperaDAO.getInstancia().update(this);		
	}


}
