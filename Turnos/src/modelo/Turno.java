package modelo;
import java.time.LocalTime;
import java.util.*;

//Faltan métodos (16/03)

public class Turno {


    public Turno(int id, Date fecha, LocalTime hora, float precio, Asistencia asistencia,
    		String justifInasistencia, Disponibilidad disponibilidad, Especialidad especialidad, Medico medico, Paciente paciente) {
    	
    	this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.precio = precio;
		this.asistencia = asistencia;
		this.justifInasistencia = justifInasistencia;
		this.disponibilidad = disponibilidad;
		this.especialidad = especialidad;
		this.paciente = paciente;
		this.medico = medico;
		
	}
    
    private int id;
	private Date fecha;
    private LocalTime hora;
    private float precio;
    private Asistencia asistencia;
    private String justifInasistencia;
    private Disponibilidad disponibilidad;
    private Especialidad especialidad;
    private Paciente paciente;
    private Medico medico;
    
	public int getId() {
		return id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
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
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	
    
}