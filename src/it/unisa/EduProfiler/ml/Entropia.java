package it.unisa.EduProfiler.ml;

import it.unisa.EduProfiler.core.Studente;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * La classe Entropia fornisce metodi per calcolare l'entropia di un insieme di dati.
 *
 * L'entropia è una misura dell'incertezza o della disorganizzazione in un insieme.
 * Viene utilizzata principalmente per valutare la purezza di una suddivisione in un
 * contesto di machine learning, ad esempio negli alberi decisionali.
 *
 * Formula per il calcolo dell'entropia:
 * H(S) = - Σ (p_i * log2(p_i))
 * Dove:
 * - p_i rappresenta la probabilità di una categoria i-esima nell'insieme.
 * - log2 è il logaritmo in base 2.
 *
 * @author Battaglia daniel
 * @author Pennarella Fabio
 */
public class Entropia {

    /**
     * Calcola l'entropia di un insieme di studenti in base all'indice accademico.
     *
     * Ogni studente è classificato in una categoria (es. Basso, Medio, Alto) in base
     * al proprio indice accademico. L'entropia quantifica la disorganizzazione
     * dell'insieme rispetto a queste categorie.
     *
     * @param studenti la lista degli studenti di cui calcolare l'entropia
     * @return il valore dell'entropia (compreso tra 0 e log2(n), dove n è il numero di categorie)
     */
    public static double calcolaEntropia(List<Studente> studenti) {
        // Controlla se la lista è vuota: in questo caso l'entropia è definita come zero
        if (studenti.isEmpty()) {
            return 0.0;
        }

        // Conta la frequenza di ogni categoria (Basso, Medio, Alto)
        Map<String, Long> frequenze = studenti.stream()
                .collect(Collectors.groupingBy(
                        studente -> studente.getCategoria().getIndiceAccademico(),
                        Collectors.counting()
                ));

        // Calcola la probabilità di ogni categoria e accumula l'entropia
        int totale = studenti.size();
        double entropia = 0.0;

        for (long frequenza : frequenze.values()) {
            double probabilita = (double) frequenza / totale;
            // Aggiorna l'entropia sottraendo p_i * log2(p_i)
            entropia -= probabilita * log2(probabilita);
        }

        return entropia;
    }

    /**
     * Metodo ausiliario per calcolare il logaritmo in base 2.
     *
     * Questo metodo è utilizzato nella formula dell'entropia per convertire
     * le probabilità in scala logaritmica base 2.
     *
     * @param x il valore di cui calcolare il logaritmo
     * @return il logaritmo base 2 di x
     */
    private static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }
}
