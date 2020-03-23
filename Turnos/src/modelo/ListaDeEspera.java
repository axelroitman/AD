package modelo;

import java.util.Collection;

public class ListaDeEspera {
	
	private int id;
	private Especialidad esp;
	private Collection<Usuario> pacientes;
	
	
	public ListaDeEspera(int id, Especialidad esp, Collection<Usuario> pacientes) {
		super();
		this.id = id;
		this.esp = esp;
		this.pacientes = pacientes;
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
