package views;

public class EspecialidadView {
	private int idEspecialidad;
	private String nombre;
    
    public EspecialidadView(int id, String nombre) {
		this.idEspecialidad = id;
		this.nombre = nombre;
	}
    
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
