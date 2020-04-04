package views;

public class EspecialidadView {
	private int id;
	private String nombre;
    
    public EspecialidadView(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
    
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
