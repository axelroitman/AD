package modelo;

public enum Disponibilidad {
	Disponible, //1
	Ocupado, //2
	AConfirmar, //3
	Confirmado, //4
	Terminado, //5
	Cancelado //6
}
//En la BD se guarda como tinyint, y los números correspondientes son los comentados al lado de cada enumeración.