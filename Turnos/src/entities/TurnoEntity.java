package entities;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import modelo.Asistencia;
import modelo.Disponibilidad;

@Entity
@Table (name="turnos")
public class TurnoEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)	
	private Date fecha;
	    
	private float precio;
    private Asistencia asistencia;
    private String justifInasistencia;
    private Disponibilidad disponibilidad;
    
    @OneToOne
	@JoinColumn(name="idEspecialidad")
    private EspecialidadEntity especialidad;

    @ManyToOne
	@JoinColumn(name="idPaciente")
    private PacienteEntity paciente;


    @ManyToOne
	@JoinColumn(name="idMedico")
    private MedicoEntity medico;
    
    public TurnoEntity() {}
    
	public TurnoEntity(int id, Date fecha, float precio, Asistencia asistencia,
			String justifInasistencia, Disponibilidad disponibilidad, EspecialidadEntity especialidad, MedicoEntity medico, PacienteEntity paciente) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.precio = precio;
		this.asistencia = asistencia;
		this.justifInasistencia = justifInasistencia;
		this.disponibilidad = disponibilidad;
		this.especialidad = especialidad;
		this.medico = medico;
		this.paciente = paciente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Asistencia getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(Asistencia asistencia) {
		this.asistencia = asistencia;
	}

	public String getJustifInasistencia() {
		return justifInasistencia;
	}

	public void setJustifInasistencia(String justifInasistencia) {
		this.justifInasistencia = justifInasistencia;
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public EspecialidadEntity getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(EspecialidadEntity especialidad) {
		this.especialidad = especialidad;
	}

	public int getId() {
		return id;
	}

	public PacienteEntity getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteEntity paciente) {
		this.paciente = paciente;
	}

	public MedicoEntity getMedico() {
		return medico;
	}

	public void setMedico(MedicoEntity medico) {
		this.medico = medico;
	}
    
   
}
