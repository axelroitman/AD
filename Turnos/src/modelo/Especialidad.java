package modelo;

import views.EspecialidadView;

//Faltan métodos (16/03)

public class Especialidad {
	
   	private int idEspecialidad;
	private String nombre;
    
    public Especialidad(int id, String nombre) {
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
	
	public EspecialidadView toView() {
		return new EspecialidadView(idEspecialidad, nombre);
	}

}