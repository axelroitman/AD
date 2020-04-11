ALTER TABLE dbo.turnos
  ADD idMedico INT,
  idPaciente INT

  GO
  
ALTER TABLE medicos
DROP COLUMN idAgenda

GO