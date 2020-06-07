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
    private int asistencia;
    private String justifInasistencia;
    private int disponibilidad;
    
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
		this.justifInasistencia = justifInasistencia;
		this.especialidad = especialidad;
		this.medico = medico;
		this.paciente = paciente;
		setAsistencia(asistencia);
		setDisponibilidad(disponibilidad);
	}

	public TurnoEntity(Date fecha, EspecialidadEntity especialidad, MedicoEntity medico) {
		super();
		this.fecha = fecha;
		this.especialidad = especialidad;
		this.medico = medico;
		this.asistencia = 3;
		this.disponibilidad = 1;
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
		Asistencia as = null;
		if(asistencia == 1) {
			as = Asistencia.Asiste;
		}
		if(asistencia == 2) {
			as = Asistencia.NoAsiste;
		}
		if(asistencia == 3) {
			as = Asistencia.NoConfirmo;
		}
		return as;
	}

	public void setAsistencia(Asistencia asistencia) {
		if(Asistencia.Asiste == asistencia) {
			this.asistencia = 1;
		}
		if(Asistencia.NoAsiste == asistencia) {
			this.asistencia = 2;
		}
		if(Asistencia.NoConfirmo == asistencia) {
			this.asistencia = 3;
		}
	}

	public String getJustifInasistencia() {
		return justifInasistencia;
	}

	public void setJustifInasistencia(String justifInasistencia) {
		this.justifInasistencia = justifInasistencia;
	}

	public Disponibilidad getDisponibilidad() {
		Disponibilidad dis = null;
		if(disponibilidad == 1) {
			dis = Disponibilidad.Disponible;
		}
		if(disponibilidad == 2) {
			dis = Disponibilidad.Programado;
		}
		if(disponibilidad == 3) {
			dis = Disponibilidad.AConfirmar;
		}
		if(disponibilidad == 4) {
			dis = Disponibilidad.Confirmado;
		}
		if(disponibilidad == 5) {
			dis = Disponibilidad.Terminado;
		}
		if(disponibilidad == 6) {
			dis = Disponibilidad.Cancelado;
		}
		return dis;		
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		if(Disponibilidad.Disponible == disponibilidad) {
			this.disponibilidad = 1;
		}
		if(Disponibilidad.Programado == disponibilidad) {
			this.disponibilidad = 2;
		}
		if(Disponibilidad.AConfirmar == disponibilidad) {
			this.disponibilidad = 3;
		}
		if(Disponibilidad.Confirmado == disponibilidad) {
			this.disponibilidad = 4;
		}
		if(Disponibilidad.Terminado == disponibilidad) {
			this.disponibilidad = 5;
		}
		if(Disponibilidad.Cancelado == disponibilidad) {
			this.disponibilidad = 6;
		}

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
