USE [AD]
GO
---------------------------------1 USUARIO----------------------------------------
INSERT INTO usuarios (usr,pass,nombre,telefono,dni,fechaNac) values ('lmontes','montes123','Lucas Montes', '1534758412','42512362','1992-02-08')
GO
--------------------------- 9 PACIENTES  (5 con la cuota al dia y 4 no)-------------------------------------------------
INSERT INTO pacientes(idUsuario,fechaVtoCuota) VALUES (1,'2020-10-07')
GO

INSERT INTO pacientes(idUsuario,fechaVtoCuota) VALUES (2,'2020-09-08')
GO

INSERT INTO pacientes(idUsuario,fechaVtoCuota) VALUES (3,'2020-11-05')
GO

INSERT INTO pacientes(idUsuario,fechaVtoCuota) VALUES (4,'2020-07-15')
GO

INSERT INTO pacientes(idUsuario,fechaVtoCuota) VALUES (5,'2020-12-23')
GO

INSERT INTO pacientes(idUsuario,fechaVtoCuota) VALUES (6,'2020-03-10')
GO

INSERT INTO pacientes(idUsuario,fechaVtoCuota) VALUES (7,'2020-01-01')
GO

INSERT INTO pacientes(idUsuario,fechaVtoCuota) VALUES (8,'2020-04-22')
GO

INSERT INTO pacientes(idUsuario,fechaVtoCuota) VALUES (9,'2020-05-18')
GO

-------------------------- 9 MEDICOS ---------------------------------------------------------------------------------------

INSERT INTO medicos (matricula ,idUsuario) VALUES ('1425' ,10)
GO

INSERT INTO medicos (matricula ,idUsuario) VALUES ('4242' ,11)
GO

INSERT INTO medicos (matricula ,idUsuario) VALUES ('3425' ,12)
GO

INSERT INTO medicos (matricula ,idUsuario) VALUES ('1423' ,18)
GO

INSERT INTO medicos (matricula ,idUsuario) VALUES ('3478' ,13)
GO

INSERT INTO medicos (matricula ,idUsuario) VALUES ('1234' ,14)
GO

INSERT INTO medicos (matricula ,idUsuario) VALUES ('2020' ,15)
GO

INSERT INTO medicos (matricula ,idUsuario) VALUES ('7321' ,16)
GO

INSERT INTO medicos (matricula ,idUsuario) VALUES ('6182' ,17)
GO
-----------------------------------------------------4 MEDICOS Y A LA VEZ PACIENTES--------------------------------------
INSERT INTO medicos (matricula ,idUsuario) VALUES ('8850' ,1)
GO
INSERT INTO medicos (matricula ,idUsuario) VALUES ('7058' ,2)
GO


INSERT INTO pacientes(idUsuario,fechaVtoCuota) VALUES (10,'2020-10-10')
GO
INSERT INTO pacientes(idUsuario,fechaVtoCuota) VALUES (11,'2020-09-08')
GO

--------------------------------------MEDICO_ESPECIALIDAD---------------------------------------------------------------
--id:1	especialidad:Cardiologo  -> 3 medicos
--id:2	especialidad:Oftalmologo -> 4 medicos
--id:3	especialidad:Neurologo   -> 6 medicos
--id:4	especialidad:Dermatologo -> 3 medicos
--id:5	especialidad:Dentista    -> 4 medicos
--id:6	especialidad:Neumonologo -> 1 solo medico

---------4 medicos que tienen 1 especialidad-----------------

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('1425',3)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('4242',2)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('3425',3)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('8850',5)
GO

---------4 medicos que tienen 2 especialidades-----------------
INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('7058',3)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('7058',4)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('1234',2)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('1234',3)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('2020',3)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('2020',4)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('1423',1)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('1423',5)
GO
---------3 medicos que tienen 3 especialidad-----------------

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('3478',1)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('3478',2)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('3478',5)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('7321', 1)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('7321',3)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('7321',5)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('6182',2)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('6182',4)
GO

INSERT INTO medico_especialidades (idMedico ,idEspecialidad) VALUES ('6182',6)
GO

--------------------------------LISTA DE ESPERA----------------------------------------------------------------------------
-------2 personas que esperan tener un turno de una especialidad especifica CON un medico especifico--------------
INSERT INTO listaDeEspera (idEspecialidad ,idMedico ,idPaciente) VALUES (1,'6182',1)
GO

INSERT INTO listaDeEspera (idEspecialidad ,idMedico ,idPaciente) VALUES (4,'1423',2)
GO

-------2 personas que esperan tener un turno de una especialidad especifica SIN un medico especifico--------------

INSERT INTO listaDeEspera (idEspecialidad ,idPaciente) VALUES (6,3)
GO

INSERT INTO listaDeEspera (idEspecialidad ,idPaciente) VALUES (6,3)
GO

-----------------------------ESPECIALIDADES CORREGIDAS------------------------------------
UPDATE [dbo].[especialidades] SET [nombre] = 'Cardiología' WHERE id=1
GO

UPDATE [dbo].[especialidades] SET [nombre] = 'Oftalmología' WHERE id=2
GO

UPDATE [dbo].[especialidades] SET [nombre] = 'Neurología' WHERE id=3
GO

UPDATE [dbo].[especialidades] SET [nombre] = 'Dermatología' WHERE id=4
GO

UPDATE [dbo].[especialidades] SET [nombre] = 'Odontología' WHERE id=5
GO

UPDATE [dbo].[especialidades] SET [nombre] = 'Neumonología' WHERE id=6
GO