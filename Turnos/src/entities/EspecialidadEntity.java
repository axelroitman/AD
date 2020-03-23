package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="especialidades")
public class EspecialidadEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	private String nombre;
    private String dia;
	
    public EspecialidadEntity(int id, String nombre, String dia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dia = dia;
	}
    
    public EspecialidadEntity() {}

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

	public int getId() {
		return id;
	}
    
}
