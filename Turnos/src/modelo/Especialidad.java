package modelo;

//Faltan métodos (16/03)

public class Especialidad {
	
   	private int id;
	private String nombre;
    
    public Especialidad(int id, String nombre) {
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