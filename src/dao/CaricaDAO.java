package dao;

import java.util.ArrayList;

import entity.Carica;

public interface CaricaDAO {
	
	
    Boolean  salvaCaricamento(Carica c);
	
	ArrayList<Carica> cercaCaricamentiUtente(String email);
	
	 ArrayList<Carica> cercaCaricamentiElemento(String idElemento);
	 
	boolean eliminaCaricamento(String email, String idElemento);

}
