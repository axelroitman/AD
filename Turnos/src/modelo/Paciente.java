package modelo;
import java.util.*;

//Faltan metodos (16/03)

public class Paciente extends Usuario {

    public Paciente(String usr, String pass, String nombre, String telefono,
    		String dni, Date fechaNac,Date fechaVtoCuota) {
    	
		super(usr, pass, nombre, telefono, dni, fechaNac);
		this.fechaVtoCuota = fechaVtoCuota;
	}
    
    private int id;
	private Date fechaVtoCuota;
    
	public Date getFechaVtoCuota() {
		return fechaVtoCuota;
	}
	public void setFechaVtoCuota(Date fechaVtoCuota) {
		this.fechaVtoCuota = fechaVtoCuota;
	}
	public int getId() {
		return id;
	}

}
