package views;

import java.time.LocalTime;
import java.util.Date;

import modelo.Asistencia;
import modelo.Disponibilidad;

public class TurnoView {


    public TurnoView(int id, Date fecha, float precio, Asistencia asistencia,
    		String justifInasistencia, Disponibilidad disponibilidad, EspecialidadView especialidad, MedicoView medico, PacienteView paciente) {
    	
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
    
    private int id;
	private Date fecha;
    private float precio;
    private Asistencia asistencia;
    private String justifInasistencia;
    private Disponibilidad disponibilidad;
    private EspecialidadView especialidad;
    private PacienteView paciente;
    private MedicoView medico;

    
	public int getId() {
		return id;
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
	public EspecialidadView getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(EspecialidadView especialidad) {
		this.especialidad = especialidad;
	}
	public PacienteView getPaciente() {
		return paciente;
	}
	public void setPaciente(PacienteView paciente) {
		this.paciente = paciente;
	}
	public MedicoView getMedico() {
		return medico;
	}
	public void setMedico(MedicoView medico) {
		this.medico = medico;
	}
	
}
