package entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@JoinColumn(name= "idEspecialidad")
	private EspecialidadEntity idEspecialidad;
	
	@ManyToOne
	@JoinColumn(name="idMedico")
	private MedicoEntity idMedico;
	
	@OneToOne
	@JoinColumn(name="idPaciente")
	private PacienteEntity idPaciente;

	public ListaDeEsperaEntity() {}
		
	public ListaDeEsperaEntity(int id, EspecialidadEntity esp, PacienteEntity paciente, MedicoEntity medico) {
		super();
		this.id = id;
		this.idEspecialidad = esp;
		this.idPaciente = paciente;
		this.idMedico = medico;
	}

	public EspecialidadEntity getEsp() {
		return idEspecialidad;
	}

	public void setEsp(EspecialidadEntity esp) {
		this.idEspecialidad = esp;
	}

	public PacienteEntity getPaciente() {
		return idPaciente;
	}

	public void setPaciente(PacienteEntity paciente) {
		this.idPaciente = paciente;
	}

	public int getId() {
		return id;
	}

	public MedicoEntity getMedico() {
		return idMedico;
	}

	public void setMedico(MedicoEntity medico) {
		this.idMedico = medico;
	}

}
