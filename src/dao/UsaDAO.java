package dao;

import entity.Usa;

import java.util.ArrayList;


public interface UsaDAO {
	
	Boolean salvaUtilizzo(Usa u);
	
	ArrayList<Usa> cercaUtilizziUtente(String email);
	
	ArrayList<Usa> cercaUtilizziElemento(String idElemento);

}
