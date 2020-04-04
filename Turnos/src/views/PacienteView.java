package views;

import java.util.Date;

public class PacienteView extends UsuarioView {
	public PacienteView(int id, int idUsr, String usr, String pass, String nombre, String telefono,
    		String dni, Date fechaNac,Date fechaVtoCuota) {
    	
		super(idUsr,usr, pass, nombre, telefono, dni, fechaNac);
		this.fechaVtoCuota = fechaVtoCuota;
		this.id = id;
	}
    private int id;
	private Date fechaVtoCuota;
    
	
	public int getId() {
		return id;
	}
	public Date getFechaVtoCuota() {
		return fechaVtoCuota;
	}
	public void setFechaVtoCuota(Date fechaVtoCuota) {
		this.fechaVtoCuota = fechaVtoCuota;
	}
}
