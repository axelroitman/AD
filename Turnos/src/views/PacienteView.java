package views;

import java.util.Date;

public class PacienteView extends UsuarioView {
	public PacienteView(int id, int idUsr, String usr, String pass, String nombre, String telefono,
    		String dni, Date fechaNac,Date fechaVtoCuota) {
		super(idUsr,usr, pass, nombre, telefono, dni, fechaNac);
    	System.out.println(idUsr);
    	System.out.println(id);
		this.fechaVtoCuota = fechaVtoCuota;
		this.idPaciente = id;
	}
    private int idPaciente;
	private Date fechaVtoCuota;
    
	
	public int getIdPaciente() {
		return idPaciente;
	}
	public Date getFechaVtoCuota() {
		return fechaVtoCuota;
	}
	public void setFechaVtoCuota(Date fechaVtoCuota) {
		this.fechaVtoCuota = fechaVtoCuota;
	}
}
