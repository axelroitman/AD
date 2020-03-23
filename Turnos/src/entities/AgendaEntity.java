package entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="agendas")
public class AgendaEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@Transient
	private Collection<TurnoEntity> turnos;

	public AgendaEntity(int id, Collection<TurnoEntity> turnos) {
		super();
		this.id = id;
		this.turnos = turnos;
	}
	
	public AgendaEntity() {}

	public Collection<TurnoEntity> getTurnos() {
		return turnos;
	}

	public void setTurnos(Collection<TurnoEntity> turnos) {
		this.turnos = turnos;
	}

	public int getId() {
		return id;
	}
	
}
