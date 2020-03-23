package views;

import java.time.LocalTime;
import java.util.Date;

import modelo.Asistencia;
import modelo.Disponibilidad;

public class TurnoView {


    public TurnoView(int id, Date fecha, LocalTime hora, float precio, Asistencia asistencia,
    		String justifInasistencia, Disponibilidad disponibilidad, EspecialidadView especialidad) {
    	
    	this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.precio = precio;
		this.asistencia = asistencia;
		this.justifInasistencia = justifInasistencia;
		this.disponibilidad = disponibilidad;
		this.especialidad = especialidad;
		
	}
    
    private int id;
	private Date fecha;
    private LocalTime hora;
    private float precio;
    private Asistencia asistencia;
    private String justifInasistencia;
    private Disponibilidad disponibilidad;
    private EspecialidadView especialidad;
    
    
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
	public EspecialidadView getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(EspecialidadView especialidad) {
		this.especialidad = especialidad;
	}
}
