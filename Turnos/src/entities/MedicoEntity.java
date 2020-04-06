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
	
    @ManyToOne
	@JoinColumn(name= "") //En "name" hay que aclarar el nombre del atributo de la tabla Agenda con el que se va a hacer join. Dejo en blanco por ahora 23/3.
    private AgendaEntity agenda;


	public MedicoEntity() {}    
    
	public MedicoEntity(String matricula, Collection<EspecialidadEntity> especialidades, AgendaEntity agenda) {
		super();
		this.matricula = matricula;
		this.especialidades = especialidades;
		this.agenda = agenda;
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

	public AgendaEntity getAgenda() {
		return agenda;
	}

	public void setAgenda(AgendaEntity agenda) {
		this.agenda = agenda;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
    
}
