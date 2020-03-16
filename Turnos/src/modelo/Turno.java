package modelo;
import java.time.LocalTime;
import java.util.*;

//Faltan métodos (16/03)

public class Turno {


    public Turno(Date fecha, LocalTime hora, float precio, boolean disponible, Asistencia asistencia,
			Especialidad especialidad) {
    	
		this.fecha = fecha;
		this.hora = hora;
		this.precio = precio;
		this.disponible = disponible;
		this.asistencia = asistencia;
		this.especialidad = especialidad;
		
	}
    
	private Date fecha;
    private LocalTime hora;
    private float precio;
    private boolean disponible;
    private Asistencia asistencia;
    private Especialidad especialidad;
    
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
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Asistencia getAsistencia() {
		return asistencia;
	}
	public void setAsistencia(Asistencia asistencia) {
		this.asistencia = asistencia;
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
    
}