package modelo;

import java.util.Collection;

public class ListaDeEspera {
	
	private int id;
	private Especialidad esp;
	private Collection<Usuario> pacientes;
	private Medico medico;
	
	
	public ListaDeEspera(int id, Especialidad esp, Collection<Usuario> pacientes, Medico medico) {
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
	public Collection<Usuario> getPacientes() {
		return pacientes;
	}
	public void setPacientes(Collection<Usuario> pacientes) {
		this.pacientes = pacientes;
	}

}
