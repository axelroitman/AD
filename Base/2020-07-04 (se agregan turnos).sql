USE [AD]
GO

--------------------------------TURNOS-------------------------------------------------------------
--idEspecialidad=3 & matricula=m1425 & duracion=40 & horaInicial=10:00:00 & horaFinal=11:30:00 & fechaInicial=15/07/2020 & fechaFinal=16/07/2020
--SON 6 TURNOS EN EL FUTURO-->DE LOS CUALES TIENE 2 OCUPADOS

INSERT INTO turnos
           (precio ,asistencia ,disponibilidad ,idEspecialidad ,idMedico,fecha) 
     VALUES (600 ,3 , 1 , 3 , 'm1425','15/07/2020 10:00:00.000')
GO

INSERT INTO turnos
           (precio ,asistencia ,disponibilidad ,idEspecialidad ,idMedico ,idPaciente ,fecha)--CONFIRMO ASISTENCIA
     VALUES (600 ,1 ,4, 3 , 'm1425' ,2,'15/07/2020 10:40:00.000')
GO

INSERT INTO turnos
           (precio ,asistencia,disponibilidad ,idEspecialidad ,idMedico ,idPaciente ,fecha)--TURNO PROGRAMADO, SIN CONFIRMAR ASISTENCIA
     VALUES (600 ,3 ,2 , 3 , 'm1425' ,5,'15/07/2020 11:20:00.000')
GO

INSERT INTO turnos
           (precio ,asistencia ,disponibilidad ,idEspecialidad ,idMedico ,fecha)
     VALUES (600 ,3 , 1 , 3 , 'm1425' ,'16/07/2020 10:00:00.000')
GO

INSERT INTO turnos
           (precio ,asistencia,disponibilidad ,idEspecialidad ,idMedico,fecha)
     VALUES (600 ,3 , 1 , 3 , 'm1425' ,'16/07/2020 10:40:00.000')
GO

INSERT INTO turnos
           (precio ,asistencia,disponibilidad ,idEspecialidad ,idMedico ,fecha)
     VALUES (600 ,3 , 1 , 3 , 'm1425','16/07/2020 11:20:00.000')
GO

--------------------------------------------------------------------------------------------------------------------
----idEspecialidad= & matricula=ma3478 & duracion=40 & horaInicial=10:00:00 & horaFinal=11:30:00 & fechaInicial=01/07/2020 & fechaFinal=02/07/2020
--4 TURNOS LIBRES Y 2 OCUPADOS QUE YA PASARON( uno cancelado y el otro se cumplio correctamente)


INSERT INTO turnos
           (precio ,asistencia ,disponibilidad ,idEspecialidad ,idMedico,fecha) 
     VALUES (700 ,3 , 1 , 5 , 'p1423','01/07/2020 10:00:00.000')
GO

INSERT INTO turnos
           (precio ,asistencia ,justifInasistencia,disponibilidad ,idEspecialidad ,idMedico ,idPaciente ,fecha)--NO ASISTE, TURNO CANCELADO 
     VALUES (700 ,2 ,'tengo un inconveniente familiar, por eso no puedo asistir', 6, 5 , 'p1423' ,1,'01/07/2020 10:40:00.000')
GO

INSERT INTO turnos
           (precio ,asistencia,disponibilidad ,idEspecialidad ,idMedico ,idPaciente ,fecha)-- ASISTENCIA CONFIRMADA, TURNO TERMINADO
     VALUES (700 ,1 ,5 , 5 , 'p1423' ,5,'01/07/2020 11:20:00.000')
GO

INSERT INTO turnos
           (precio ,asistencia ,disponibilidad ,idEspecialidad ,idMedico ,fecha)
     VALUES (700 ,3 , 1 , 5 , 'p1423' ,'02/07/2020 10:00:00.000')
GO

INSERT INTO turnos
           (precio ,asistencia,disponibilidad ,idEspecialidad ,idMedico,fecha)
     VALUES (700 ,3 , 1 , 5 , 'p1423' ,'02/07/2020 10:40:00.000')
GO

INSERT INTO turnos
           (precio ,asistencia,disponibilidad ,idEspecialidad ,idMedico ,fecha)
     VALUES (700 ,3 , 1 , 5 , 'p1423','02/07/2020 11:20:00.000')
GO
