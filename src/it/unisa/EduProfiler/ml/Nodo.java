package it.unisa.EduProfiler.ml;

/**
 * La classe Nodo rappresenta un nodo di un albero decisionale.
 * Ogni nodo può rappresentare un attributo da cui si fa la divisione
 * (ad esempio, mediaVoti, mediaOreDiStudio, mediaAttività) o una previsione (foglia).
 * I nodi non foglia sono utilizzati per decidere il percorso basato sul valore di un attributo,
 * mentre le foglie contengono il valore finale dell'indice accademico.
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */
public class Nodo {
    private String attributo;  // Attributo da cui si fa la divisione (mediaVoti, mediaOreDiStudio, mediaAttività)
    private String valorePredizione;  // Se il nodo è una foglia, il valore dell'indice accademico
    private Nodo sinistro;  // Nodo sinistro (Bassa)
    private Nodo centrale;  // Nodo centrale (Media)
    private Nodo destro;  // Nodo destro (Alta)
    private boolean isFoglia;  // Se il nodo è una foglia (cioè fa una previsione)

    /**
     * Costruisce un nodo non foglia con un attributo da cui effettuare la divisione.
     *
     * @param attributo l'attributo che viene usato per la divisione (es. mediaVoti, mediaOreDiStudio)
     */
    public Nodo(String attributo) {
        this.attributo = attributo;
        this.isFoglia = false;
    }

    /**
     * Costruisce una foglia dell'albero decisionale che contiene il valore della previsione.
     *
     * @param valorePredizione il valore che rappresenta l'indice accademico previsto (Basso, Medio, Alto)
     * @param isFoglia indica se il nodo è una foglia (true se è una foglia, false altrimenti)
     */
    public Nodo(String valorePredizione, boolean isFoglia) {
        this.attributo = "indiceAccademico";
        this.valorePredizione = valorePredizione;
        this.isFoglia = isFoglia;
    }

    /**
     * Restituisce l'attributo del nodo (es. mediaVoti, mediaOreDiStudio).
     *
     * @return l'attributo del nodo
     */
    public String getAttributo() {
        return attributo;
    }

    /**
     * Imposta l'attributo del nodo.
     *
     * @param attributo l'attributo da assegnare al nodo
     */
    public void setAttributo(String attributo) {
        this.attributo = attributo;
    }

    /**
     * Restituisce il valore della previsione del nodo (solo se è una foglia).
     *
     * @return il valore della previsione (Basso, Medio, Alto) o null se non è una foglia
     */
    public String getValorePredizione() {
        return valorePredizione;
    }

    /**
     * Imposta il valore della previsione per il nodo.
     *
     * @param valorePredizione il valore da assegnare come previsione
     */
    public void setValorePredizione(String valorePredizione) {
        this.valorePredizione = valorePredizione;
    }

    /**
     * Restituisce il nodo sinistro (Basso).
     *
     * @return il nodo sinistro
     */
    public Nodo getSinistro() {
        return sinistro;
    }

    /**
     * Imposta il nodo sinistro (Basso).
     *
     * @param sinistro il nodo sinistro da impostare
     */
    public void setSinistro(Nodo sinistro) {
        this.sinistro = sinistro;
    }

    /**
     * Restituisce il nodo centrale (Media).
     *
     * @return il nodo centrale
     */
    public Nodo getCentrale() {
        return centrale;
    }

    /**
     * Imposta il nodo centrale (Media).
     *
     * @param centrale il nodo centrale da impostare
     */
    public void setCentrale(Nodo centrale) {
        this.centrale = centrale;
    }

    /**
     * Restituisce il nodo destro (Alta).
     *
     * @return il nodo destro
     */
    public Nodo getDestro() {
        return destro;
    }

    /**
     * Imposta il nodo destro (Alta).
     *
     * @param destro il nodo destro da impostare
     */
    public void setDestro(Nodo destro) {
        this.destro = destro;
    }

    /**
     * Restituisce true se il nodo è una foglia (cioè fa una previsione), false altrimenti.
     *
     * @return true se il nodo è una foglia, false altrimenti
     */
    public boolean isFoglia() {
        return isFoglia;
    }

    /**
     * Imposta se il nodo è una foglia (cioè fa una previsione).
     *
     * @param isFoglia true se il nodo è una foglia, false altrimenti
     */
    public void setFoglia(boolean isFoglia) {
        this.isFoglia = isFoglia;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "attributo='" + attributo + '\'' +
                ", valorePredizione='" + valorePredizione + '\'' +
                ", sinistro=" + sinistro +
                ", centrale=" + centrale +
                ", destro=" + destro +
                '}';
    }
}