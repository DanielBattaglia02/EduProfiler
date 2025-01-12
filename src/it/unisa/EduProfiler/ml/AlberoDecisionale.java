package it.unisa.EduProfiler.ml;

import it.unisa.EduProfiler.core.Studente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * La classe AlberoDecisionale implementa un albero decisionale per prevedere l'indice accademico
 * degli studenti basandosi su diversi attributi come la media dei voti, le ore di studio settimanali e le attività extra.
 * L'albero decisionale è costruito ricorsivamente, suddividendo gli studenti in base agli attributi e alle categorie di indice accademico.
 * La classe fornisce anche metodi per la predizione dell'indice accademico per uno studente dato un albero decisionale.
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */
public class AlberoDecisionale {

    /**
     * Metodo per costruire l'albero decisionale a partire da un dataset di studenti.
     * La costruzione parte dall'attributo "mediaVoti" e viene ricorsivamente suddiviso in base agli attributi rimanenti.
     *
     * @param studenti la lista degli studenti da utilizzare per costruire l'albero decisionale
     * @return il nodo radice dell'albero decisionale
     */
    public static Nodo costruisciAlbero(List<Studente> studenti) {
        // Lista degli attributi da utilizzare per la suddivisione
        List<String> attributi = new ArrayList<>(Arrays.asList("mediaVoti", "mediaOreDiStudio", "mediaAttivita"));

        // Sceglie il miglior attributo da utilizzare
        String migliorAttributo = scegliMigliorAttributo(studenti, attributi);

        // Costruisce l'albero decisionale ricorsivamente
        return costruisciNodo(studenti, migliorAttributo, attributi);
    }

    /**
     * Metodo ricorsivo per costruire un nodo dell'albero decisionale.
     * Suddivide gli studenti in base al valore dell'attributo corrente e costruisce i nodi figli.
     *
     * @param studenti la lista di studenti da suddividere
     * @param attributoCorrente l'attributo su cui suddividere gli studenti (mediaVoti, mediaOreDiStudio, mediaAttività)
     * @param attributi la lista degli attributi da utilizzare per la suddivisione
     * @return il nodo creato per l'attributo corrente
     */
    private static Nodo costruisciNodo(List<Studente> studenti, String attributoCorrente, List<String> attributi) {
        // Se il sottoinsieme di studenti è vuoto, ritorna null
        if (studenti.isEmpty()) {
            return null;
        }

        // Crea un nodo decisionale per l'attributo corrente
        Nodo nodo = new Nodo(attributoCorrente);

        // Suddivide gli studenti in base al valore dell'attributo corrente
        List<Studente> sinistra = new ArrayList<>();
        List<Studente> destra = new ArrayList<>();
        List<Studente> centrale = new ArrayList<>();

        // Suddivide gli studenti nelle tre categorie: Bassa, Alta, Media
        for (Studente studente : studenti) {
            String valoreAttributo = getValoreAttributo(studente, attributoCorrente);
            if ("Bassa".equals(valoreAttributo)) {
                sinistra.add(studente);
            } else if ("Alta".equals(valoreAttributo)) {
                destra.add(studente);
            } else {
                centrale.add(studente);  // Categoria "media"
            }
        }

        // Crea una copia della lista degli attributi rimanenti per evitare modifiche dirette durante la ricorsione
        List<String> attributiRestanti = new ArrayList<>(attributi);
        attributiRestanti.remove(attributoCorrente);

        // Sceglie il miglior attributo per il sottoalbero ricorsivo
        String migliorAttributo = null;
        if (attributiRestanti.isEmpty()) {
            migliorAttributo = "indiceAccademico";
            attributiRestanti.add("indiceAccademico");
            attributiRestanti.add("indiceAccademico_end");
        }
        else if (attributiRestanti.contains("indiceAccademico_end"))
        {
            // Se siamo arrivati all'ultimo attributo (indiceAccademico), crea una foglia con l'indice accademico
            return new Nodo(getIndiceAccademico(studenti), true);  // Nodo foglia con il valore dell'indice accademico
        }
        else
        {
            migliorAttributo = scegliMigliorAttributo(studenti, attributiRestanti);
        }

        // Costruisce ricorsivamente i sottoalberi per ciascuna categoria (Bassa, Alta, Media)
        nodo.setSinistro(costruisciNodo(sinistra, migliorAttributo, attributiRestanti));  // Nodo per "Basso"
        nodo.setDestro(costruisciNodo(destra, migliorAttributo, attributiRestanti));      // Nodo per "Alta"
        nodo.setCentrale(costruisciNodo(centrale, migliorAttributo, attributiRestanti));  // Nodo per "Media"

        return nodo;
    }

    /**
     * Metodo per scegliere il miglior attributo da utilizzare per la suddivisione, basandosi sul guadagno di informazione.
     *
     * @param studenti la lista degli studenti
     * @param attributi la lista degli attributi da esaminare
     * @return il miglior attributo in base al guadagno di informazione
     */
    private static String scegliMigliorAttributo(List<Studente> studenti, List<String> attributi) {
        String migliorAttributo = null;
        double massimoGuadagno = Double.NEGATIVE_INFINITY;

        // Esamina ogni attributo per calcolare il guadagno di informazione
        for (String attributo : attributi) {
            double guadagno = InformationGain.calcolaInformationGain(studenti, attributo);
            if (guadagno > massimoGuadagno) {
                massimoGuadagno = guadagno;
                migliorAttributo = attributo;
            }
        }

        return migliorAttributo;
    }

    /**
     * Metodo per ottenere il valore di un attributo di uno studente (es. mediaVoti, mediaOreDiStudio).
     *
     * @param studente lo studente da cui ottenere il valore dell'attributo
     * @param attributo il nome dell'attributo da ottenere
     * @return il valore dell'attributo per lo studente
     */
    private static String getValoreAttributo(Studente studente, String attributo) {
        switch (attributo) {
            case "mediaVoti":
                return studente.getCategoria().getMediaVoti();
            case "mediaOreDiStudio":
                return studente.getCategoria().getMediaOreDiStudio();
            case "mediaAttivita":
                return studente.getCategoria().getMediaAttivitaExtra();
            case "indiceAccademico":
                return studente.getCategoria().getIndiceAccademico();
            default:
                return "";
        }
    }

    /**
     * Metodo per predire l'indice accademico per uno studente dato l'albero decisionale.
     * Questo metodo segue il percorso dell'albero in base ai valori degli attributi dello studente,
     * gestendo i casi in cui i nodi figli siano nulli.
     *
     * @param nodo il nodo corrente dell'albero
     * @param studente lo studente per cui fare la previsione
     * @return l'indice accademico predetto per lo studente (Basso, Medio, Alto)
     */
    public static String prediciIndice(Nodo nodo, Studente studente) {
        if (nodo == null) {
            return null; // Se il nodo è null, non c'è alcuna previsione
        }

        if (nodo.isFoglia()) {
            return nodo.getValorePredizione(); // Se il nodo è una foglia, ritorna la predizione
        }

        //System.out.println(nodo.getAttributo() + nodo.getSinistro() + nodo.getCentrale() + nodo.getDestro());

        // Esamina il valore dell'attributo dello studente per decidere quale ramo seguire
        String valoreAttributo = getValoreAttributo(studente, nodo.getAttributo());

        Nodo prossimoNodo = null;

        // Determina il prossimo nodo in base al valore dell'attributo
        if ("Bassa".equals(valoreAttributo)) {
            prossimoNodo = nodo.getSinistro();
            if (prossimoNodo == null) {
                prossimoNodo = nodo.getCentrale();
                if (prossimoNodo == null) {
                    prossimoNodo = nodo.getDestro();
                }
            }
        } else if ("Alta".equals(valoreAttributo)) {
            prossimoNodo = nodo.getDestro();
            if (prossimoNodo == null) {
                prossimoNodo = nodo.getCentrale();
                if (prossimoNodo == null) {
                    prossimoNodo = nodo.getSinistro();
                }
            }
        } else {
            prossimoNodo = nodo.getCentrale();
            if (prossimoNodo == null) {
                prossimoNodo = nodo.getSinistro();
                if (prossimoNodo == null) {
                    prossimoNodo = nodo.getDestro();
                }
            }
        }

        // Continua la previsione con il prossimo nodo
        return prediciIndice(prossimoNodo, studente);
    }

    /**
     * Metodo per apprenderel'indice accademico del sottoinsieme di studenti, restituendo il valore più comune.
     *
     * @param studenti la lista di studenti per calcolare l'indice accademico
     * @return l'indice accademico predetto (Basso, Medio, Alto)
     */
    private static String getIndiceAccademico(List<Studente> studenti) {
        //Apprendiamo l'indice accademico
        String indiceAccademico = studenti.get(0).getCategoria().getIndiceAccademico();
        for (Studente studente : studenti) {
            String indice = studente.getCategoria().getIndiceAccademico();
            if (indice == null) {
                continue;  // Ignora gli studenti con indice accademico nullo
            }
            if (!indice.equals(indiceAccademico)) {
                return indiceAccademico;  // Se gli indici non sono uguali, ritorna il primo trovato
            }
        }
        return indiceAccademico;  // Ritorna il valore comune dell'indice accademico
    }
}
