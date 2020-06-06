package entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="listaDeEspera")
public class ListaDeEsperaEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name= "id")
	private EspecialidadEntity esp;
	
	@OneToOne
	@JoinColumn(name="matricula")
	private MedicoEntity medico;
	
	@Transient
	private Collection<PacienteEntity> pacientes;

	public ListaDeEsperaEntity() {}
		
	public ListaDeEsperaEntity(int id, EspecialidadEntity esp, Collection<PacienteEntity> pacientes, MedicoEntity medico) {
		super();
		this.id = id;
		this.esp = esp;
		this.pacientes = pacientes;
		this.medico = medico;
	}

	public EspecialidadEntity getEsp() {
		return esp;
	}

	public void setEsp(EspecialidadEntity esp) {
		this.esp = esp;
	}

	public Collection<PacienteEntity> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Collection<PacienteEntity> pacientes) {
		this.pacientes = pacientes;
	}

	public int getId() {
		return id;
	}

	public MedicoEntity getMedico() {
		return medico;
	}

	public void setMedico(MedicoEntity medico) {
		this.medico = medico;
	}

}
