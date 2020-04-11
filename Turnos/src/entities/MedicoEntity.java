package entities;
import java.util.Collection;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table (name="medicos")
public class MedicoEntity {

	@Id
	private String matricula;
	
	@Transient
    private Collection<EspecialidadEntity> especialidades;
    
	@OneToOne
	@JoinColumn(name="documento")
	private UsuarioEntity usuario;

	public MedicoEntity() {}    
	public MedicoEntity(UsuarioEntity usuario, String matricula /*, Collection<EspecialidadEntity> especialidades, AgendaEntity agenda*/) {
		super();
		this.usuario = usuario;
		this.matricula = matricula;
		/*this.especialidades = especialidades;*/
	}

	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Collection<EspecialidadEntity> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Collection<EspecialidadEntity> especialidades) {
		this.especialidades = especialidades;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
    
}
