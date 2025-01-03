package it.unisa.EduProfiler.ml;

import it.unisa.EduProfiler.core.Studente;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * La classe InformationGain calcola il guadagno di informazione di un attributo
 * rispetto alla previsione di un'etichetta target (indice accademico).
 *
 * Il guadagno di informazione misura quanto un attributo aiuta a ridurre l'entropia
 * dell'insieme di dati, fornendo una metrica utile per scegliere il miglior attributo
 * per suddividere i dati in un albero decisionale.
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */
public class InformationGain {

    /**
     * Calcola il guadagno di informazione per un attributo rispetto all'indice accademico.
     *
     * @param studenti la lista degli studenti da analizzare
     * @param attributo il nome dell'attributo per cui calcolare il guadagno di informazione
     * @return il valore del guadagno di informazione
     *
     * Formula:
     * InformationGain(A) = Entropia(S) - Σ ((|Sv| / |S|) * Entropia(Sv))
     * Dove:
     * - Entropia(S) è l'entropia iniziale dell'insieme completo.
     * - Sv è un sottoinsieme di dati con un valore specifico per l'attributo A.
     * - |Sv| / |S| è il peso proporzionale del sottoinsieme rispetto all'insieme iniziale.
     */
    public static double calcolaInformationGain(List<Studente> studenti, String attributo) {
        // Entropia dell'insieme iniziale
        double entropiaIniziale = Entropia.calcolaEntropia(studenti);

        // Suddividi i dati in base ai valori dell'attributo
        Map<String, List<Studente>> suddivisioni = studenti.stream()
                .collect(Collectors.groupingBy(
                        studente -> getValoreAttributo(studente, attributo)
                ));

        // Calcola l'entropia media ponderata dei sottoinsiemi
        double entropiaCondizionale = 0.0;
        int totale = studenti.size();

        // Per ogni sottoinsieme generato dalla suddivisione, calcola l'entropia ponderata
        for (List<Studente> sottoinsieme : suddivisioni.values()) {
            double peso = (double) sottoinsieme.size() / totale;
            entropiaCondizionale += peso * Entropia.calcolaEntropia(sottoinsieme);
        }

        // Guadagno di informazione: entropia iniziale meno l'entropia condizionale
        return entropiaIniziale - entropiaCondizionale;
    }

    /**
     * Ottiene il valore di un attributo specifico per uno studente.
     * Questo metodo permette di generalizzare il calcolo del guadagno di informazione
     * per diversi attributi disponibili nella classe Studente.
     *
     * @param studente l'istanza dello studente da cui estrarre l'attributo
     * @param attributo il nome dell'attributo da ottenere (es. "mediaVoti")
     * @return il valore dell'attributo come stringa
     *
     * Nota: Gli attributi validi sono:
     * - "mediaVoti"
     * - "mediaOreDiStudio"
     * - "mediaAttività"
     * Se un attributo non è valido, viene generata un'eccezione IllegalArgumentException.
     */
    private static String getValoreAttributo(Studente studente, String attributo) {
        switch (attributo) {
            case "mediaVoti":
                return studente.getCategoria().getMediaVoti();
            case "mediaOreDiStudio":
                return studente.getCategoria().getMediaOreDiStudio();
            case "mediaAttivita":
                return studente.getCategoria().getMediaAttivitaExtra();
            default:
                throw new IllegalArgumentException("Attributo non valido: " + attributo);
        }
    }
}
