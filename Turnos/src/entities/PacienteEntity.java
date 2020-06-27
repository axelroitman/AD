package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table (name="pacientes")
public class PacienteEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private int idPaciente;
	
	@OneToOne
	@JoinColumn(name="idUsuario")
	private UsuarioEntity usuario;
	
	@Temporal(TemporalType.DATE)
	private Date fechaVtoCuota;
	
	public PacienteEntity() {}
	
	public PacienteEntity(int id, UsuarioEntity usuario, Date fechaVtoCuota) {
		super();
		this.idPaciente = id;
		this.usuario = usuario;
		this.fechaVtoCuota = fechaVtoCuota;
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

	public int getId() {
		return idPaciente;
	}
}
