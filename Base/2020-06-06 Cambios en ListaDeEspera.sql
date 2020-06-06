ALTER TABLE dbo.listaDeEspera DROP COLUMN idUsuario
go

ALTER TABLE dbo.listaDeEspera ADD idPaciente int NOT NULL;
go