package control;

import dao.UtenteDAO;
import dao.jdbc.JDBCUtenteDAO;
import entity.Utente;

public class Controller {
 
    private UtenteDAO utenteDAO = new JDBCUtenteDAO();
    
    
    public Utente effettuaLogin(String email, String password) {
        return utenteDAO.login(email, password);
    }
}
