package it.unisa.EduProfiler.ml;

import it.unisa.EduProfiler.core.Studente;
import it.unisa.EduProfiler.ml.AlberoDecisionale;
import it.unisa.EduProfiler.ml.Nodo;
import it.unisa.EduProfiler.utils.GenerazioneDataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe di test per la classe AlberoDecisionale.
 * Testa la corretta costruzione dell'albero decisionale e la predizione dell'indice accademico
 * per diversi studenti, verificando i vari scenari di media alta, media e bassa.
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */
public class AlberoDecisionaleTest
{
    private Nodo albero;
    private List<Studente> studenti;

    /**
     * Imposta l'ambiente di test prima di ogni test.
     * Inizializza una lista di studenti e costruisce l'albero decisionale.
     */
    @BeforeEach
    public void setUp()
    {
        studenti = new ArrayList<>();
        studenti = GenerazioneDataSet.generaStudenti();

        // Costruisci l'albero decisionale con gli studenti
        albero = AlberoDecisionale.costruisciAlbero(studenti);
    }

    /**
     * Testa la costruzione dell'albero decisionale.
     * Verifica che l'albero non sia null dopo la costruzione.
     */
    @Test
    public void testCostruzioneAlbero()
    {
        // Verifica che l'albero sia stato costruito correttamente
        assertNotNull(albero, "L'albero decisionale non dovrebbe essere null");
    }

    /**
     * Testa la predizione di un'indice accademico alto.
     * Verifica che l'indice accademico predetto sia "Alto".
     */
    @Test
    public void testPredizioneIndiceAccademicoAlto()
    {
        // Verifica che un nuovo studente con una media alta venga predetto correttamente
        Studente nuovoStudente = new Studente(28, 20, 15, 0);

        String predizione = AlberoDecisionale.prediciIndice(albero, nuovoStudente);
        // L'indice accademico predetto dovrebbe essere "Alto"
        assertEquals("Alto", predizione, "L'indice accademico predetto dovrebbe essere 'Alto'");
    }

    /**
     * Testa la predizione di un'indicie accademico medio.
     * Verifica che l'indice accademico predetto sia "Medio".
     */
    @Test
    public void testPredizioneIndiceAccademicoMedio() {
        // Verifica che un nuovo studente con una media media venga predetto correttamente
        Studente nuovoStudente = new Studente(20, 18, 12, 0);

        String predizione = AlberoDecisionale.prediciIndice(albero, nuovoStudente);
        // L'indice accademico predetto dovrebbe essere "Medio"
        assertEquals("Medio", predizione, "L'indice accademico predetto dovrebbe essere 'Media'");
    }

    /**
     * Testa la predizione di un'indice accademico basso.
     * Verifica che l'indice accademico predetto sia "Basso".
     */
    @Test
    public void testPredizioneIndiceAccademicoBasso() {
        // Verifica che un nuovo studente con una media bassa venga predetto correttamente
        Studente nuovoStudente = new Studente(18, 9, 5, 0);

        String predizione = AlberoDecisionale.prediciIndice(albero, nuovoStudente);
        assertEquals("Medio", predizione, "L'indice accademico predetto dovrebbe essere 'Basso'");
    }

    /**
     * Testa la predizione dell'indice accademico quando l'albero decisionale è null.
     * Verifica che la predizione restituisca null in questo caso.
     */
    @Test
    public void testPredizioneIndiceAccademicoNull()
    {
        // Verifica che se l'albero è null, la predizione restituisce null
        assertNull(AlberoDecisionale.prediciIndice(null, null), "La predizione dovrebbe essere null se l'albero è null");
    }

    /**
     * Test dell'intero dataset.
     */
    @Test
    public void testDATASET()
    {
        for (Studente studente : studenti)
        {
            Studente nuovoStudente = new Studente(
                    studente.getMediaVoti(),
                    studente.getMediaOreDiStudio(),
                    studente.getMediaAttivitaExtra(),
                    0
            );

            String predizione = AlberoDecisionale.prediciIndice(albero, nuovoStudente);

            // Confronta la predizione con il risultato atteso
            assertEquals(studente.getCategoria().getIndiceAccademico(), predizione,
                    "Predizione errata.\neffettivo: " + studente.getCategoria().getIndiceAccademico() +
                            "\npredetto: " + predizione +
                            "\nStudente: " + studente);
        }
    }
}