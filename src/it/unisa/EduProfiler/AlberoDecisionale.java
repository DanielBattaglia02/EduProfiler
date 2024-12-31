package it.unisa.EduProfiler;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe AlberoDecisionale implementa un albero decisionale per prevedere l'indice accademico
 * degli studenti basandosi su diversi attributi come la media dei voti, le ore di studio e le attività extra.
 * L'albero è costruito ricorsivamente suddividendo gli studenti in base agli attributi e alle categorie di indice accademico.
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */
public class AlberoDecisionale {

    /**
     * Metodo per costruire l'albero decisionale. La costruzione parte dall'attributo "mediaVoti".
     *
     * @param studenti la lista degli studenti da utilizzare per costruire l'albero decisionale
     * @return il nodo radice dell'albero decisionale
     */
    public static Nodo costruisciAlbero(List<Studente> studenti) {
        return costruisciNodo(studenti, "mediaVoti");
    }

    /**
     * Metodo ricorsivo per costruire un nodo dell'albero decisionale. Suddivide gli studenti
     * in base al valore dell'attributo corrente e costruisce i nodi figli.
     *
     * @param studenti la lista di studenti da suddividere
     * @param attributoCorrente l'attributo su cui suddividere gli studenti (mediaVoti, mediaOreDiStudio, mediaAttività)
     * @return il nodo creato
     */
    private static Nodo costruisciNodo(List<Studente> studenti, String attributoCorrente) {
        // Se il sottoinsieme è vuoto, ritorna null (nodo foglia)
        if (studenti.isEmpty()) {
            return null;
        }

        // Crea un nodo decisionale per l'attributo corrente
        Nodo nodo = new Nodo(attributoCorrente);

        // Suddividere i dati in base al valore dell'attributo corrente
        List<Studente> sinistra = new ArrayList<>();
        List<Studente> destra = new ArrayList<>();
        List<Studente> centrale = new ArrayList<>();

        // Suddividere gli studenti nelle tre categorie: Basso, Alta, Media
        for (Studente studente : studenti) {
            String valoreAttributo = getValoreAttributo(studente, attributoCorrente);
            if ("Basso".equals(valoreAttributo)) {
                sinistra.add(studente);
            } else if ("Alta".equals(valoreAttributo)) {
                destra.add(studente);
            } else {
                centrale.add(studente);  // Aggiungi alla lista "media"
            }
        }

        // Determina il prossimo attributo per i nodi figli
        String prossimoAttributo = prossimoAttributo(attributoCorrente);

        // Se siamo arrivati all'ultimo attributo (indiceAccademico), crea una foglia con l'indice accademico
        if (prossimoAttributo == null) {
            return new Nodo(calcolaIndiceAccademico(studenti), true);  // Nodo foglia con il valore dell'indice accademico
        }

        // Ricorsivamente costruisce i sottoalberi
        nodo.setSinistro(costruisciNodo(sinistra, prossimoAttributo));  // Nodo per "Basso"
        nodo.setDestro(costruisciNodo(destra, prossimoAttributo));      // Nodo per "Alta"
        if (!centrale.isEmpty()) {
            nodo.setCentrale(costruisciNodo(centrale, prossimoAttributo));  // Nodo per "Media"
        }

        return nodo;
    }

    /**
     * Metodo per calcolare l'indice accademico del sottoinsieme di studenti, restituendo il valore più comune.
     *
     * @param studenti la lista di studenti per calcolare l'indice accademico
     * @return l'indice accademico predetto (Basso, Medio, Alto)
     */
    private static String calcolaIndiceAccademico(List<Studente> studenti) {
        // Calcoliamo l'indice accademico come il più comune tra gli studenti nel sottoinsieme
        String indiceAccademico = studenti.get(0).getCategoria().getIndiceAccademico();
        for (Studente studente : studenti) {
            String indice = studente.getCategoria().getIndiceAccademico();
            if (indice == null) {
                // Se l'indice accademico è null, ignoriamo questo studente nella comparazione
                continue;
            }
            if (!indice.equals(indiceAccademico)) {
                // Se gli indici non sono gli stessi, ritorniamo solo il primo trovato (logica semplice)
                return indiceAccademico;
            }
        }
        return indiceAccademico;
    }

    /**
     * Metodo per ottenere il valore dell'attributo corrente di uno studente (es. mediaVoti, mediaOreDiStudio).
     *
     * @param studente lo studente di cui ottenere il valore dell'attributo
     * @param attributo il nome dell'attributo da ottenere
     * @return il valore dell'attributo per lo studente
     */
    private static String getValoreAttributo(Studente studente, String attributo) {
        switch (attributo) {
            case "mediaVoti":
                return studente.getCategoria().getMediaVoti();
            case "mediaOreDiStudio":
                return studente.getCategoria().getMediaOreDiStudio();
            case "mediaAttività":
                return studente.getCategoria().getMediaAttivitaExtra();
            case "indiceAccademico":
                return studente.getCategoria().getIndiceAccademico();
            default:
                return "";
        }
    }

    /**
     * Metodo per determinare il prossimo attributo da analizzare nell'albero decisionale.
     *
     * @param attributoCorrente l'attributo corrente su cui si sta lavorando
     * @return l'attributo successivo da utilizzare, o null se non ci sono più attributi
     */
    private static String prossimoAttributo(String attributoCorrente) {
        if ("mediaVoti".equals(attributoCorrente)) {
            return "mediaOreDiStudio";  // Se siamo su mediaVoti, il prossimo attributo è mediaOreDiStudio
        } else if ("mediaOreDiStudio".equals(attributoCorrente)) {
            return "mediaAttività";  // Se siamo su mediaOreDiStudio, il prossimo attributo è mediaAttività
        } else if ("mediaAttività".equals(attributoCorrente)) {
            return "indiceAccademico";  // Se siamo su mediaAttività, il prossimo attributo è indiceAccademico
        } else {
            return null;  // Fine dell'albero, siamo arrivati al nodo foglia
        }
    }

    /**
     * Metodo per predire l'indice accademico per un nuovo studente dato l'albero decisionale.
     *
     * @param nodo il nodo corrente dell'albero
     * @param studente lo studente per cui fare la previsione
     * @return l'indice accademico predetto per lo studente (Basso, Medio, Alto)
     */
    public static String prediciIndice(Nodo nodo, Studente studente) {
        if (nodo == null) {
            return null;  // Se il nodo è null, non c'è alcuna previsione
        }

        if (nodo.isFoglia()) {
            return nodo.getValorePredizione();  // Se il nodo è una foglia, ritorna la predizione
        }

        String valoreAttributo = getValoreAttributo(studente, nodo.getAttributo());
        if ("Basso".equals(valoreAttributo)) {
            return prediciIndice(nodo.getSinistro(), studente);  // Vai al nodo sinistro
        } else if ("Alta".equals(valoreAttributo)) {
            return prediciIndice(nodo.getDestro(), studente);  // Vai al nodo destro
        } else {
            return prediciIndice(nodo.getCentrale(), studente);  // Vai al nodo centrale
        }
    }
}
