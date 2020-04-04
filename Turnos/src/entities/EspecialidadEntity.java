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
	
    public EspecialidadEntity(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
    
    public EspecialidadEntity() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}
    
}
