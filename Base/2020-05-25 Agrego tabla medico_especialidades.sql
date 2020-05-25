use AD

CREATE TABLE medico_especialidades(
	idMedico_especialidades int identity,
	idMedico varchar(100) not null,
	idEspecialidad int not null,
	constraint pk_medico_especialidades primary key (idMedico_especialidades),
	constraint fk_medico foreign key (idMedico) references dbo.medicos (matricula),
	constraint fk_especialidad foreign key (idEspecialidad) references dbo.especialidades (id)
)
go