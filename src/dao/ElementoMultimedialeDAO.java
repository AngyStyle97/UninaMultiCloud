package dao;
import entity.ElementoMultimediale;
import java.util.ArrayList;

public interface ElementoMultimedialeDAO {
	
	boolean  salvaElemento(ElementoMultimediale  e);
	
	boolean eliminaElemento(String  IdElemento);
	
	 ElementoMultimediale cercaPerId(String IdElemento);
	
	boolean  aggiornaElemento(ElementoMultimediale e);
	
	ArrayList<ElementoMultimediale> cercaElementi(String titolo, String autore);

}
