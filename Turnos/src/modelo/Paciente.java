package modelo;
import java.util.*;

import views.PacienteView;

//Faltan metodos (16/03)

public class Paciente extends Usuario {

    public Paciente(int id, int idUsr, String usr, String pass, String nombre, String telefono,
    		String dni, Date fechaNac,Date fechaVtoCuota) {
    	
		super(idUsr, usr, pass, nombre, telefono, dni, fechaNac);
		this.fechaVtoCuota = fechaVtoCuota;
		this.idPaciente = id;
	}
    
    private int idPaciente;
	private Date fechaVtoCuota;
    
	public Date getFechaVtoCuota() {
		return fechaVtoCuota;
	}
	public void setFechaVtoCuota(Date fechaVtoCuota) {
		this.fechaVtoCuota = fechaVtoCuota;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	
	public PacienteView toView() {
		
		return new PacienteView(idPaciente, super.getIdUsr(), super.getUsr(), super.getPass(), super.getNombre(), super.getTelefono(), super.getDni(), super.getFechaNac(), fechaVtoCuota);
	}


}
