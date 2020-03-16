package modelo;

//Faltan métodos (16/03)

public class Especialidad {

   public Especialidad(String nombre, String dia) {
		this.nombre = nombre;
		this.dia = dia;
	}

	private String nombre;
    private String dia;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
    
    
}