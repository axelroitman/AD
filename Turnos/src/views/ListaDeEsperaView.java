package views;

import java.util.Collection;

public class ListaDeEsperaView {
	private int id;
	private EspecialidadView esp;
	private Collection<UsuarioView> pacientes;
	
	
	public ListaDeEsperaView(int id, EspecialidadView esp, Collection<UsuarioView> pacientes) {
		super();
		this.id = id;
		this.esp = esp;
		this.pacientes = pacientes;
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
	public Collection<UsuarioView> getPacientes() {
		return pacientes;
	}
	public void setPacientes(Collection<UsuarioView> pacientes) {
		this.pacientes = pacientes;
	}
}
