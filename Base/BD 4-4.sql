use master
go

create database AD
go

use AD
go

create table medicos(
	matricula varchar(100) not null,
	idUsuario int not null,
	idAgenda int not null,
	constraint pk_medicos primary key (matricula)
)
go
create table pacientes(
	id int identity,
	idUsuario int not null,
	fechaVtoCuota date not null,
	constraint pk_pacientes primary key (id)
)
go
create table usuarios(
	id int identity,
	usr varchar(250) not null,
	pass varchar(250) not null,
	nombre varchar(250) not null,
	telefono varchar(250) not null,
	dni varchar(250) not null,
	fechaNac date not null,
	constraint pk_usuarios primary key (id)
)
go
create table especialidades(
	id int identity,
	nombre varchar(250) not null,
	constraint pk_especialidades primary key (id)
)
go
create table listaDeEspera(
	id int identity,
	idEspecialidad int,
	idMedico int,
	idUsuario int not null,
	constraint pk_listaDeEspera primary key (id)
)
go
create table turnos(
	id int identity,
	fecha date not null,
	hora time not null,
	precio float not null,
	asistencia tinyint,
	justifInasistencia varchar(6000),
	disponibilidad tinyint not null,
	idEspecialidad int not null,
	constraint pk_turnos primary key (id)
)
go


-------------------------Usuarios---------------------------
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('jalmeida','pepitox','Jose Almeida', '55354000','10256365','1984-12-24')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('ldanton','1234','Luis Danton', '435612345','15236589','1956-10-02')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('dsherpov','123456','David Sherpov', '23465894','35698785','1993-03-15')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('furrutia','jonico43','Felipe Urrutia', '35795123','36256536','1996-04-16')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('mfilos','1523','Matilde Filos', '35652569','13456789','1995-04-24')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('dduncan','534fa','Debora Duncan', '12548965','12345670','1965-12-31')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('molivares','fes142','Micaela Olivares', '12356986','50698369','1958-03-30')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('pcirigliano','gdsfa42','Pedro Cirigliano', '35698645','60123456','1969-09-06')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('maurelio','guante2','Marco Aurelio', '31245678','43256896','1962-04-07')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('oteselado','a50253','Orlando Teselado', '35968456','12365896','1963-09-08')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('apires','rau5301','Agustina Pires', '95236548','13563365','1964-09-09')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('lblistein','gils152','Lucia Blistein', '46581236','40325523','1964-07-21')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('lhidalgo','huks42','Lorenzo Hidalgo', '12354687','65523523','1997-05-10')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('lsummers','12345','Linda Summers', '12347689','20258258','1998-08-11')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('mportela','rhost24152','Martina Portela', '45678956','36159951','1999-06-12')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('efitzgerald','blu32dad','Ella Fitzgerald', '13456795','35753753','2000-04-13')
go
insert into usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('lpetruccelli','opywon2','Ludovico Petruccelli', '89546321','12951357','2000-06-14')
go

------------------------Especialidades-----------------------------
insert into especialidades (nombre) values ('Cardiologo')
go
insert into especialidades (nombre) values ('Oftalmologo')
go
insert into especialidades (nombre) values ('Neurologo')
go
insert into especialidades (nombre) values ('Dermatologo')
go
insert into especialidades (nombre) values ('Dentista')
go
insert into especialidades (nombre) values ('Neumonologo')
go
