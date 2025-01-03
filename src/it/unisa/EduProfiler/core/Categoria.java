package it.unisa.EduProfiler.core;

/**
 * Classe che rappresenta la categoria di uno studente in base alla sua media voti, media ore di studio e media attività extra.
 * Inoltre, calcola l'indice accademico complessivo dello studente.
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */
public class Categoria {
    private String mediaVoti;
    private String mediaOreDiStudio;
    private String mediaAttivitaExtra;
    private String indiceAccademico = null;

    /**
     * Costruttore che determina la categoria per ogni attributo e calcola l'indice accademico.
     *
     * @param mediaVoti la media dei voti dello studente
     * @param mediaOreDiStudio la media delle ore di studio dello studente
     * @param mediaAttivitaExtra la media delle attività extra dello studente
     * @param type il tipo di calcolo per l'indice accademico (1 per calcolare, 0 per non calcolare)
     */
    public Categoria(double mediaVoti, double mediaOreDiStudio, int mediaAttivitaExtra, int type)
    {
        switch ((int) mediaVoti) {
            case 18, 19, 20, 21, 22:
                this.mediaVoti = "Bassa";
                break;
            case 23, 24, 25, 26:
                this.mediaVoti = "Media";
                break;
            case 27, 28, 29, 30:
                this.mediaVoti = "Alta";
                break;
            default:
                this.mediaVoti = "Non valido";
        }

        switch ((int) mediaOreDiStudio) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                this.mediaOreDiStudio = "Bassa";
                break;
            case 11, 12, 13, 14, 15, 16, 17, 18, 19, 20:
                this.mediaOreDiStudio = "Media";
                break;
            default:
                this.mediaOreDiStudio = "Alta";
        }

        switch (mediaAttivitaExtra) {
            case 0, 1, 2:
                this.mediaAttivitaExtra = "Bassa";
                break;
            case 3, 4, 5:
                this.mediaAttivitaExtra = "Media";
                break;
            default:
                this.mediaAttivitaExtra = "Alta";
        }

        if (type == 1)
        {
            this.indiceAccademico = calcolaIndiceAccademico();
        }
    }

    /**
     * Restituisce la categoria relativa alla media dei voti dello studente.
     *
     * @return la categoria della media dei voti
     */
    public String getMediaVoti() {
        return mediaVoti;
    }

    /**
     * Restituisce la categoria relativa alla media delle ore di studio dello studente.
     *
     * @return la categoria della media delle ore di studio
     */
    public String getMediaOreDiStudio() {
        return mediaOreDiStudio;
    }

    /**
     * Restituisce la categoria relativa alla media delle attività extra dello studente.
     *
     * @return la categoria della media delle attività extra
     */
    public String getMediaAttivitaExtra() {
        return mediaAttivitaExtra;
    }

    /**
     * Restituisce l'indice accademico complessivo dello studente.
     *
     * @return l'indice accademico dello studente
     */
    public String getIndiceAccademico() {
        return indiceAccademico;
    }

    /**
     * Metodo che calcola l'indice accademico complessivo dello studente, basandosi sui punteggi delle categorie.
     *
     * @return la categoria finale dell'indice accademico (Basso, Medio, Alto)
     */
    private String calcolaIndiceAccademico() {
        // Calcola il punteggio per ogni categoria
        int punteggioVoti = getPunteggioCategoria(mediaVoti);
        int punteggioOreDiStudio = getPunteggioCategoria(mediaOreDiStudio);
        int punteggioAttivitaExtra = getPunteggioCategoria(mediaAttivitaExtra);

        // Somma i punteggi
        int punteggioTotale = punteggioVoti + punteggioOreDiStudio + punteggioAttivitaExtra;

        // Determina la categoria finale dello studente in base al punteggio totale
        if (punteggioTotale <= 3) {
            return "Basso";
        } else if (punteggioTotale <= 6) {
            return "Medio";
        } else {
            return "Alto";
        }
    }

    /**
     * Metodo per convertire le categorie in punteggi numerici. La categoria "Bassa" vale 1, "Media" vale 2 e "Alta" vale 3.
     *
     * @param categoria la categoria da convertire
     * @return il punteggio numerico corrispondente alla categoria
     */
    private int getPunteggioCategoria(String categoria) {
        switch (categoria) {
            case "Bassa":
                return 1;
            case "Media":
                return 2;
            case "Alta":
                return 3;
            default:
                return 0;
        }
    }

    /**
     * Metodo che restituisce una rappresentazione in formato stringa dell'oggetto Categoria.
     *
     * @return la stringa che rappresenta le categorie e l'indice accademico dello studente
     */
    @Override
    public String toString() {
        return "Categoria -> Media Voti: " + mediaVoti + ", " +
                "Ore Studio: " + mediaOreDiStudio + ", " +
                "Attività Extra: " + mediaAttivitaExtra + ", " +
                "Indice Accademico: " + indiceAccademico;
    }

    /**
     * Metodo principale per testare la classe Categoria.
     *
     * @param args gli argomenti della linea di comando (non utilizzati in questo caso)
     */
    public static void main(String[] args) {
        // Creazione di un oggetto Categoria con i dati dello studente
        Categoria categoria = new Categoria(24, 15, 3, 1);
        System.out.println(categoria);  // Output: Categoria -> Media Voti: Media, Ore Studio: Media, Attività Extra: Media, Indice Accademico: Medio
    }
}
