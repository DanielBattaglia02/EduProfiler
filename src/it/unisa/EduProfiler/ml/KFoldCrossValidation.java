package it.unisa.EduProfiler.ml;

import it.unisa.EduProfiler.core.Studente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * La classe contiene il metodo per effetturare la validazione incrociata "KFoldCrossValidation" del nosto modello.
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */

public class KFoldCrossValidation
{
    /**
     * Metodo per eseguire la K-Fold Cross Validation sull'albero decisionale.
     *
     * @param studenti la lista degli studenti da utilizzare per la validazione
     * @param numeroFold il numero di fold (K)
     * @return la media dell'accuratezza su tutti i fold
     */
    public static double eseguiKFold(List<Studente> studenti, int numeroFold) {
        // Misceliamo i dati per evitare bias
        Collections.shuffle(studenti);

        // Calcolare la dimensione di ogni fold
        int foldSize = studenti.size() / numeroFold;
        double accuratezzaTotale = 0;

        // Eseguiamo la K-Fold Cross Validation
        for (int i = 0; i < numeroFold; i++) {
            // Suddividere il dataset in training e test
            List<Studente> testSet = new ArrayList<>(studenti.subList(i * foldSize, (i + 1) * foldSize));
            List<Studente> trainingSet = new ArrayList<>(studenti);
            trainingSet.removeAll(testSet);

            // Costruisci l'albero decisionale usando il training set
            Nodo albero = AlberoDecisionale.costruisciAlbero(trainingSet);
            System.out.println(trainingSet.size() + "--" + testSet.size());

            // Calcola l'accuratezza sul test set
            int correttezza = 0;
            for (Studente studente : testSet) {
                String previsione = AlberoDecisionale.prediciIndice(albero, studente);
                if (previsione==null)
                {
                    return -20;
                }

                if (previsione.equals(studente.getCategoria().getIndiceAccademico())) {
                    correttezza++;
                }
            }

            // Calcoliamo l'accuratezza per il fold corrente
            double accuratezzaFold = (double) correttezza / testSet.size();
            accuratezzaTotale += accuratezzaFold;
        }

        // Ritorna la media dell'accuratezza su tutti i fold
        return accuratezzaTotale / numeroFold;
    }
}