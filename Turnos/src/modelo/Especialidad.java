package modelo;

import views.EspecialidadView;

//Faltan m�todos (16/03)

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
	
	public EspecialidadView toView() {
		return new EspecialidadView(id, nombre);
	}

}