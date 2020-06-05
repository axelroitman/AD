package test;

import daos.MedicoDAO;

public class TestConsola {

	public static void main(String[] args) {
		System.out.println(MedicoDAO.getInstancia().getMedicos());
	}
	
}
