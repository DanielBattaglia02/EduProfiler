package it.unisa.EduProfiler.ml;

import it.unisa.EduProfiler.core.Studente;
import it.unisa.EduProfiler.utils.GenerazioneDataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Classe di test di "KFoldCrossValidation".
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */
public class KFoldCrossValidationTest {

    private List<Studente> studenti;

    /**
     * Metodo eseguito prima di ogni test per preparare i dati di test.
     * Inizializza un dataset di studenti fittizi.
     */
    @BeforeEach
    public void setUp() {
        // Generiamo un dataset di studenti fittizi
        studenti = GenerazioneDataSet.generaStudenti();
    }

    /**
     * Test per verificare che la funzione K-Fold Cross Validation non lanci eccezioni
     * e che l'accuratezza calcolata sia valida.
     *
     * @throws Exception se si verifica un errore durante l'esecuzione del test
     */
    @Test
    public void testEseguiKFold() throws Exception {
        // Numero di fold per il test
        int k = 10;

        // Eseguiamo la K-Fold Cross Validation
        double accuracy = KFoldCrossValidation.eseguiKFold(studenti, k);
        System.out.println("Accuratezza media dopo " + k + "-fold Cross Validation: " + accuracy * 100 + "%");

        // Verifica che l'accuratezza non sia NaN
        assertFalse(Double.isNaN(accuracy), "L'accuratezza non deve essere NaN");

        // Verifica che l'accuratezza sia un valore compreso tra 0 e 1
        assertTrue(accuracy >= 0 && accuracy <= 1, "L'accuratezza deve essere tra 0 e 1");
    }

    /**
     * Test per verificare che l'accuratezza venga calcolata correttamente con K=1
     * (Leave-One-Out Cross Validation).
     * In questo caso, ci aspettiamo che l'accuratezza non sia un valore valido
     * e venga restituito un valore di errore (-20).
     *
     * @throws Exception se si verifica un errore durante l'esecuzione del test
     */
    @Test
    public void testEseguiKFoldConK1() throws Exception {
        int k = 1; // Leave-One-Out Cross Validation (LOO)

        // Eseguiamo la K-Fold Cross Validation
        double accuracy = KFoldCrossValidation.eseguiKFold(studenti, k);

        // Indica che la previsione non è andata a buon fine
        assertEquals(accuracy, -20, "L'accuratezza dovrebbe essere -20 in caso di errore");
    }

    /**
     * Test per verificare che il dataset di studenti non sia vuoto
     * e che non sia nullo.
     */
    @Test
    public void testDatasetNonVuoto() {
        assertNotNull(studenti, "Il dataset degli studenti non deve essere null");
        assertFalse(studenti.isEmpty(), "Il dataset degli studenti non deve essere vuoto");
    }
}
