ALTER TABLE dbo.turnos DROP COLUMN hora
go
ALTER TABLE dbo.turnos DROP COLUMN fecha
go

ALTER TABLE dbo.turnos ADD fecha datetime NULL;
go