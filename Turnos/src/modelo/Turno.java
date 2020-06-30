package modelo;
import java.time.LocalTime;
import java.util.*;

import views.TurnoView;


public class Turno {


    public Turno(int id, Date fecha, float precio, Asistencia asistencia,
    		String justifInasistencia, Disponibilidad disponibilidad, Especialidad especialidad, Medico medico, Paciente paciente) {
		
    	this.id = id;
		this.fecha = fecha;
		this.precio = precio;
		this.asistencia = asistencia;
		this.justifInasistencia = justifInasistencia;
		this.disponibilidad = disponibilidad;
		this.especialidad = especialidad;
		this.paciente = paciente;
		this.medico = medico;
		

		
	}
    public Turno(Date fecha, Especialidad especialidad, Medico medico) {
    	
		this.fecha = fecha;
		this.disponibilidad = Disponibilidad.Disponible;
		this.especialidad = especialidad;
		this.medico = medico;
		
	}   
    private int id;
	private Date fecha;
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

	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Asistencia getAsistencia() {
		return asistencia;
	}
	public void setDisponibilidad(int disponibilidad) {
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
		this.disponibilidad = dis;
	}

public void setAsistencia(int asistencia) {
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
		this.asistencia = as;
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
	
	public TurnoView toView() {
		
		if(justifInasistencia == null && paciente == null) 
		{
			return new TurnoView(id, fecha, precio, asistencia, null, disponibilidad, especialidad.toView(), medico.toView(), null);

		}
		else if(justifInasistencia == null && paciente != null) {
			return new TurnoView(id, fecha, precio, asistencia, null, disponibilidad, especialidad.toView(), medico.toView(), paciente.toView());
		}
		else if(justifInasistencia != null && paciente == null) 
		{
			return new TurnoView(id, fecha, precio, asistencia, justifInasistencia, disponibilidad, especialidad.toView(), medico.toView(), null);

		}
		else {
			return new TurnoView(id, fecha, precio, asistencia, justifInasistencia, disponibilidad, especialidad.toView(), medico.toView(), paciente.toView());
		}
		
	}
    
}