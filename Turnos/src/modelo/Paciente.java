package modelo;
import java.util.*;

//Faltan metodos (16/03)

public class Paciente extends Usuario {

    public Paciente(String usr, String pass, String nombre, String telefono,
    		String dni, Date fechaNac,Date fechaVtoCuota, Agenda agenda) {
    	
		super(usr, pass, nombre, telefono, dni, fechaNac);
		this.fechaVtoCuota = fechaVtoCuota;
		this.agenda = agenda;
	}
    
	private Date fechaVtoCuota;
    private Agenda agenda;
    
	public Date getFechaVtoCuota() {
		return fechaVtoCuota;
	}
	public void setFechaVtoCuota(Date fechaVtoCuota) {
		this.fechaVtoCuota = fechaVtoCuota;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

}