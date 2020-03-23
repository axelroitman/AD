package views;

import java.util.Collection;

public class AgendaView {
	private Collection<TurnoView> turnos;

	public Collection<TurnoView> getTurnos() {
		return turnos;
	}

	public void setTurnos(Collection<TurnoView> turnos) {
		this.turnos = turnos;
	}
}
