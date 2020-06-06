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
	
	@OneToOne
	@JoinColumn(name="id")
	private PacienteEntity paciente;

	public ListaDeEsperaEntity() {}
		
	public ListaDeEsperaEntity(int id, EspecialidadEntity esp, PacienteEntity paciente, MedicoEntity medico) {
		super();
		this.id = id;
		this.esp = esp;
		this.paciente = paciente;
		this.medico = medico;
	}

	public EspecialidadEntity getEsp() {
		return esp;
	}

	public void setEsp(EspecialidadEntity esp) {
		this.esp = esp;
	}

	public PacienteEntity getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteEntity paciente) {
		this.paciente = paciente;
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
