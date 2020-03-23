package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table (name="pacientes")
public class PacienteEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name="documento")
	private UsuarioEntity usuario;
	
	private Date fechaVtoCuota;
	
	@ManyToOne
	@JoinColumn(name= "") //En "name" hay que aclarar el nombre del atributo de la tabla Agenda con el que se va a hacer join. Dejo en blanco por ahora 23/3.
	private AgendaEntity agenda;

	public PacienteEntity() {}
	
	public PacienteEntity(int id, UsuarioEntity usuario, Date fechaVtoCuota, AgendaEntity agenda) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.fechaVtoCuota = fechaVtoCuota;
		this.agenda = agenda;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public Date getFechaVtoCuota() {
		return fechaVtoCuota;
	}

	public void setFechaVtoCuota(Date fechaVtoCuota) {
		this.fechaVtoCuota = fechaVtoCuota;
	}

	public AgendaEntity getAgenda() {
		return agenda;
	}

	public void setAgenda(AgendaEntity agenda) {
		this.agenda = agenda;
	}

	public int getId() {
		return id;
	}
}
