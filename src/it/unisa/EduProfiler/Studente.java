package it.unisa.EduProfiler;

/**
 * Classe che rappresenta uno studente, con le sue medie di voti, ore di studio e attività extra.
 * La classe crea una categoria per lo studente basata su questi valori.
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */
public class Studente {
    private double mediaOreDiStudio;
    private double mediaVoti;
    private double mediaAttivitaExtra;
    private Categoria categoria;

    /**
     * Costruttore che inizializza i dati dello studente e calcola la sua categoria.
     *
     * @param mediaVoti la media dei voti dello studente
     * @param mediaOreDiStudio la media delle ore di studio dello studente
     * @param mediaAttivitaExtra la media delle attività extra dello studente
     * @param type il tipo di calcolo per l'indice accademico (1 per calcolare, 0 per non calcolare)
     */
    public Studente(double mediaVoti, double mediaOreDiStudio, double mediaAttivitaExtra, int type) {
        this.mediaVoti = mediaVoti;
        this.mediaOreDiStudio = mediaOreDiStudio;
        this.mediaAttivitaExtra = mediaAttivitaExtra;
        this.categoria = new Categoria(this.mediaVoti, this.mediaOreDiStudio, this.mediaAttivitaExtra, type);
    }

    /**
     * Restituisce la media delle ore di studio dello studente.
     *
     * @return la media delle ore di studio dello studente
     */
    public double getMediaOreDiStudio() {
        return mediaOreDiStudio;
    }

    /**
     * Imposta la media delle ore di studio dello studente.
     *
     * @param mediaOreDiStudio la nuova media delle ore di studio
     */
    public void setMediaOreDiStudio(double mediaOreDiStudio) {
        this.mediaOreDiStudio = mediaOreDiStudio;
    }

    /**
     * Restituisce la media dei voti dello studente.
     *
     * @return la media dei voti dello studente
     */
    public double getMediaVoti() {
        return mediaVoti;
    }

    /**
     * Imposta la media dei voti dello studente.
     *
     * @param mediaVoti la nuova media dei voti
     */
    public void setMediaVoti(double mediaVoti) {
        this.mediaVoti = mediaVoti;
    }

    /**
     * Restituisce la media delle attività extra dello studente.
     *
     * @return la media delle attività extra dello studente
     */
    public double getMediaAttivitaExtra() {
        return mediaAttivitaExtra;
    }

    /**
     * Imposta la media delle attività extra dello studente.
     *
     * @param mediaAttivitaExtra la nuova media delle attività extra
     */
    public void setMediaAttivitaExtra(double mediaAttivitaExtra) {
        this.mediaAttivitaExtra = mediaAttivitaExtra;
    }

    /**
     * Restituisce la categoria dello studente, che include la classificazione delle medie
     * e l'indice accademico calcolato.
     *
     * @return la categoria dello studente
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Imposta la categoria dello studente.
     *
     * @param categoria la nuova categoria dello studente
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Metodo che restituisce una rappresentazione in formato stringa dell'oggetto Studente.
     *
     * @return una stringa che rappresenta le informazioni dello studente e la sua categoria
     */
    @Override
    public String toString() {
        return "Studente{" +
                "mediaOreDiStudio=" + mediaOreDiStudio +
                ", mediaVoti=" + mediaVoti +
                ", mediaAttivitaExtra=" + mediaAttivitaExtra +
                ", categoria=" + categoria +
                '}';
    }

    /**
     * Metodo principale per testare la classe Studente.
     *
     * @param args gli argomenti della linea di comando (non utilizzati in questo caso)
     */
    public static void main(String[] args) {
        // Creazione di un oggetto Studente con i dati dello studente
        Studente studente = new Studente(28, 22, 2, 1);
        System.out.println(studente);  // Output: Studente{mediaOreDiStudio=22.0, mediaVoti=28.0, mediaAttivitaExtra=2.0, categoria=Categoria -> Media Voti: Alta, Ore Studio: Alta, Attività Extra: Bassa, Indice Accademico: Medio}
    }
}
