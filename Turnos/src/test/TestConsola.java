package test;

import java.util.ArrayList;
import java.util.Collection;

import daos.MedicoDAO;
import modelo.Especialidad;
import modelo.Medico;

public class TestConsola {

	public static void main(String[] args) {
		Collection<Medico>med = MedicoDAO.getInstancia().getMedicos();
		Collection<Especialidad> esp = new ArrayList<Especialidad>();
		for(Medico m : med) {
			 for(Especialidad e : m.getEspecialidades()) {
				 esp.add(e);
			 }
		}
		
		for(Especialidad e : esp) {
			System.out.println(e.getNombre());
		}
	}
	
}
