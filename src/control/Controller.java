package control;

import java.util.ArrayList; 
import java.util.Date;
import javax.swing.JOptionPane;
import boundary.CaricaElementoPage;
import boundary.HomePage;
import boundary.LoginPage;
import boundary.ProfiloPage;
import boundary.ReportPlaylistPage;
import boundary.RicercaElementoPage;
import boundary.RicercaPlaylistPage;
import boundary.RiproduzioneElementoPage;
import boundary.VisualizzaPlaylistPage;
import dao.CondivisaConDAO;
import dao.ElementoMultimedialeDAO;
import dao.PlaylistDAO;
import dao.UsaDAO;
import dao.UtenteDAO;
import dao.jdbc.JDBCCondivisaConDAO;
import dao.jdbc.JDBCElementoDAO;
import dao.jdbc.JDBCPlaylistDAO;
import dao.jdbc.JDBCUsaDAO;
import dao.jdbc.JDBCUtenteDAO;
import entity.ElementoMultimediale;
import entity.Playlist;
import entity.Usa;
import entity.Utente;
import dao.CaricaDAO;
import dao.jdbc.JDBCCaricaDAO;
import entity.Carica;

public class Controller {

    private Utente utenteLoggato;
    private UtenteDAO utenteDAO;
    private PlaylistDAO playlistDAO;
    private ElementoMultimedialeDAO elementoDAO;
    private UsaDAO usaDAO;
    private CondivisaConDAO condivisaConDAO;
    private CaricaDAO caricaDAO;

    public Controller() {

        utenteDAO = new JDBCUtenteDAO();
        playlistDAO = new JDBCPlaylistDAO();
        elementoDAO = new JDBCElementoDAO();
        usaDAO = new JDBCUsaDAO();
        condivisaConDAO = new JDBCCondivisaConDAO();
        caricaDAO = new JDBCCaricaDAO();

    }

    public void avviaApplicazione() {

        mostraLogin();

    }

    public void mostraLogin() {

        LoginPage pagina = new LoginPage(this);

        pagina.setVisible(true);

    }

    /*

     * Recupera l'utente tramite email e confronta la password.

     */

    public boolean login(String email, String password) {

        if (email == null || password == null) {

            return false;

        }

        email = email.trim();

        if (email.isEmpty() || password.isEmpty()) {

            return false;

        }

        Utente utente = utenteDAO.cercaEmail(email);

        if (utente == null) {

            return false;

        }

        if (!password.equals(utente.getPassword())) {

            return false;

        }

        utenteLoggato = utente;

        return true;

    }

    public void logout() {

        utenteLoggato = null;

    }

    public void mostraMenu() {

        if (!utenteAutenticato()) {

            mostraLogin();

            return;

        }

        HomePage pagina = new HomePage(this);

        pagina.setVisible(true);

    }

    public void mostraProfilo() {

        if (!utenteAutenticato()) {

            mostraMessaggio("Nessun utente autenticato.");

            return;

        }

        Utente utenteAggiornato =

                utenteDAO.cercaEmail(utenteLoggato.getEmail());

        if (utenteAggiornato != null) {

            utenteLoggato = utenteAggiornato;

        }

        ProfiloPage pagina = new ProfiloPage(this);

        pagina.mostraProfilo(

                utenteLoggato.getNome(),

                utenteLoggato.getCognome(),

                utenteLoggato.getEmail()

        );

        pagina.setVisible(true);

    }

    public void mostraCaricaElemento() {

        if (!utenteAutenticato()) {
            mostraMessaggio("Devi effettuare il login.");
            return;
        }

        CaricaElementoPage pagina =
                new CaricaElementoPage(this);

        pagina.setVisible(true);
    }

    /*

     * Il nome CaricaElemento nel tuo mockup indica

     * l'inserimento di un elemento esistente in una playlist.

     */

    public boolean caricaElemento(

            String idElemento,

            String idPlaylist) {

        if (!utenteAutenticato()) {

            mostraMessaggio("Devi effettuare il login.");

            return false;

        }

        if (idElemento == null || idPlaylist == null) {

            return false;

        }

        idElemento = idElemento.trim();

        idPlaylist = idPlaylist.trim();

        if (idElemento.isEmpty() || idPlaylist.isEmpty()) {

            mostraMessaggio("Seleziona elemento e playlist.");

            return false;

        }

        return aggiungiElementoAPlaylist(

                idElemento,

                idPlaylist

        );

    }

    /*

     * Metodo eventualmente utilizzabile per inserire

     * un nuovo elemento nella tabella elemento_multimediale.

     */

    public boolean inserisciElemento(
            ElementoMultimediale elemento,
            double peso) {

        if (!utenteAutenticato()
                || elemento == null
                || peso <= 0) {

            return false;
        }

        ElementoMultimediale esistente =
                elementoDAO.cercaPerId(
                        elemento.getIdElemento()
                );

        if (esistente != null) {

            mostraMessaggio(
                    "Esiste già un elemento con questo ID."
            );

            return false;
        }

        /*
         * Prima inserisce il nuovo elemento nella tabella
         * elemento_multimediale.
         */
        boolean elementoInserito =
                elementoDAO.salvaElemento(elemento);

        if (!elementoInserito) {
            return false;
        }

        /*
         * Registra chi ha effettuato il caricamento
         * nella tabella carica.
         */
        Carica caricamento = new Carica(
                new Date(),
                peso,
                elemento,
                utenteLoggato
        );

        boolean caricamentoRegistrato =
                caricaDAO.salvaCaricamento(
                        caricamento
                );

        if (!caricamentoRegistrato) {

            mostraMessaggio(
                    "Elemento inserito, ma caricamento non registrato."
            );

            return false;
        }

        /*
         * Rilegge l'utente perché il trigger dovrebbe aver
         * incrementato numerocaricamenti nel database.
         */
        Utente aggiornato =
                utenteDAO.cercaEmail(
                        utenteLoggato.getEmail()
                );

        if (aggiornato != null) {
            utenteLoggato = aggiornato;
        }

        return true;
    }

    public void mostraRicercaElemento() {

        RicercaElementoPage pagina =

                new RicercaElementoPage(this);

        pagina.setVisible(true);

    }

    public ArrayList<ElementoMultimediale> cercaElemento(

            String titolo) {

        if (titolo == null) {

            return new ArrayList<>();

        }

        return elementoDAO.cercaElementi(

                titolo.trim()

        );

    }

    public void mostraRiproduzioneElemento(

            ElementoMultimediale elemento) {

        if (elemento == null) {

            mostraMessaggio("Nessun elemento selezionato.");

            return;

        }

        RiproduzioneElementoPage pagina =

                new RiproduzioneElementoPage(

                        this,

                        elemento

                );

        pagina.setVisible(true);

    }

    /*

     * Metodo chiamato da VisualizzaPlaylistPage.

     * Chiede l'ID dell'elemento da aprire.

     */

    public void mostraVisualizzaElementoDaPlaylist(

            String idPlaylist) {

        if (idPlaylist == null) {

            return;

        }

        String idElemento = JOptionPane.showInputDialog(

                null,

                "Inserisci l'ID dell'elemento da visualizzare:",

                "Visualizza elemento",

                JOptionPane.QUESTION_MESSAGE

        );

        if (idElemento == null) {

            return;

        }

        idElemento = idElemento.trim();

        if (idElemento.isEmpty()) {

            mostraMessaggio("Inserisci un ID valido.");

            return;

        }

        ElementoMultimediale elemento =

                elementoDAO.cercaPerId(idElemento);

        if (elemento == null) {

            mostraMessaggio("Elemento non trovato.");

            return;

        }

        mostraRiproduzioneElemento(elemento);

    }

    public boolean registraRiproduzione(

            String idElemento,

            double tempoUtilizzo) {

        if (!utenteAutenticato()) {

            mostraMessaggio("Devi effettuare il login.");

            return false;

        }

        if (idElemento == null || tempoUtilizzo < 0) {

            return false;

        }

        ElementoMultimediale elemento =

                elementoDAO.cercaPerId(idElemento);

        if (elemento == null) {

            mostraMessaggio("Elemento non trovato.");

            return false;

        }

        Usa utilizzo = new Usa(

                new Date(),

                tempoUtilizzo,

                utenteLoggato,

                elemento

        );

        return usaDAO.salvaUtilizzo(utilizzo);

    }

    public void mostraRicercaPlaylist() {

        RicercaPlaylistPage pagina =

                new RicercaPlaylistPage(this);

        pagina.setVisible(true);

    }

    public ArrayList<Playlist> cercaPlaylist(

            String nomePlaylist,

            String tipoPlaylist) {

        ArrayList<Playlist> risultati =

                new ArrayList<>();

        String nome = nomePlaylist == null

                ? ""

                : nomePlaylist.trim();

        String tipo = tipoPlaylist == null

                ? ""

                : tipoPlaylist.trim().toLowerCase();

        ArrayList<Playlist> playlistTrovate;

        if (!tipo.isEmpty()) {

            playlistTrovate =

                    playlistDAO.cercaPerTipo(tipo);

        } else if (utenteAutenticato()) {

            playlistTrovate =

                    playlistDAO.cercaPerUtente(

                            utenteLoggato.getEmail()

                    );

        } else {

            return risultati;

        }

        if (playlistTrovate == null) {

            return risultati;

        }

        for (Playlist playlist : playlistTrovate) {

            boolean nomeCorrispondente =

                    nome.isEmpty()

                    || playlist.getNomePlaylist()

                            .toLowerCase()

                            .contains(nome.toLowerCase());

            if (nomeCorrispondente) {

                risultati.add(playlist);

            }

        }

        return risultati;

    }

    public void mostraVisualizzaPlaylist(

            Playlist playlist) {

        if (playlist == null) {

            mostraMessaggio(

                    "Nessuna playlist selezionata."

            );

            return;

        }

        VisualizzaPlaylistPage pagina =

                new VisualizzaPlaylistPage(

                        this,

                        playlist

                );

        pagina.setVisible(true);

    }

    public void mostraVisualizzaPlaylist(

            String idPlaylist) {

        if (idPlaylist == null) {

            return;

        }

        Playlist playlist =

                playlistDAO.cercaPerId(idPlaylist);

        if (playlist == null) {

            mostraMessaggio("Playlist non trovata.");

            return;

        }

        mostraVisualizzaPlaylist(playlist);

    }

    public boolean aggiungiElementoAPlaylist(

            String idElemento,

            String idPlaylist) {

        if (idElemento == null || idPlaylist == null) {

            return false;

        }

        idElemento = idElemento.trim();

        idPlaylist = idPlaylist.trim();

        ElementoMultimediale elemento =

                elementoDAO.cercaPerId(idElemento);

        Playlist playlist =

                playlistDAO.cercaPerId(idPlaylist);

        if (elemento == null) {

            mostraMessaggio("Elemento non trovato.");

            return false;

        }

        if (playlist == null) {

            mostraMessaggio("Playlist non trovata.");

            return false;

        }

        return playlistDAO.aggiungiElemento(

                idPlaylist,

                idElemento

        );

    }

    public boolean rimuoviElementoDaPlaylist(

            String idElemento,

            String idPlaylist) {

        if (idElemento == null || idPlaylist == null) {

            return false;

        }

        return playlistDAO.rimuoviElemento(

                idPlaylist.trim(),

                idElemento.trim()

        );

    }

    public boolean eliminaPlaylist(

            String idPlaylist) {

        if (idPlaylist == null

                || idPlaylist.trim().isEmpty()) {

            return false;

        }

        return playlistDAO.eliminaPlaylist(

                idPlaylist.trim()

        );

    }

    public boolean condividiPlaylist(

            String idPlaylist,

            String emailUtente) {

        if (!utenteAutenticato()) {

            mostraMessaggio("Devi effettuare il login.");

            return false;

        }

        if (idPlaylist == null || emailUtente == null) {

            return false;

        }

        idPlaylist = idPlaylist.trim();

        emailUtente = emailUtente.trim();

        if (idPlaylist.isEmpty()

                || emailUtente.isEmpty()) {

            return false;

        }

        Playlist playlist =

                playlistDAO.cercaPerId(idPlaylist);

        if (playlist == null) {

            mostraMessaggio("Playlist non trovata.");

            return false;

        }

        Utente destinatario =

                utenteDAO.cercaEmail(emailUtente);

        if (destinatario == null) {

            mostraMessaggio(

                    "Utente destinatario non trovato."

            );

            return false;

        }

        if (emailUtente.equalsIgnoreCase(

                utenteLoggato.getEmail())) {

            mostraMessaggio(

                    "Non puoi condividere la playlist con te stesso."

            );

            return false;

        }

        /*

         * Il trigger nel database controllerà che

         * la playlist sia di tipo condivisa.

         */

        return condivisaConDAO.salvaCondivisione(

                emailUtente,

                idPlaylist

        );

    }

    public void mostraReportPlaylist() {

        if (!utenteAutenticato()) {

            mostraMessaggio("Devi effettuare il login.");

            return;

        }

        Utente utenteAggiornato =

                utenteDAO.cercaEmail(

                        utenteLoggato.getEmail()

                );

        if (utenteAggiornato != null) {

            utenteLoggato = utenteAggiornato;

        }

        ArrayList<Playlist> playlistUtente =

                playlistDAO.cercaPerUtente(

                        utenteLoggato.getEmail()

                );

        int numeroPlaylist =

                playlistUtente == null

                        ? 0

                        : playlistUtente.size();

        int numeroCaricamenti =

                utenteLoggato.getNumeroCaricamenti();

        ReportPlaylistPage pagina =

                new ReportPlaylistPage(

                        this,

                        numeroPlaylist,

                        numeroCaricamenti

                );

        pagina.setVisible(true);

    }

    public void generaReportPlaylist() {

        mostraReportPlaylist();

    }

    public Utente getUtenteLoggato() {

        return utenteLoggato;

    }

    private boolean utenteAutenticato() {

        return utenteLoggato != null;

    }

    private void mostraMessaggio(String messaggio) {

        JOptionPane.showMessageDialog(

                null,

                messaggio

        );

    }

}