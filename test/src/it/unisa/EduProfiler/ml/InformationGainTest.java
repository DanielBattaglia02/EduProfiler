package it.unisa.EduProfiler.ml;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import it.unisa.EduProfiler.core.Studente;
import it.unisa.EduProfiler.utils.GenerazioneDataSet;
import it.unisa.EduProfiler.ml.InformationGain;

import java.util.List;

/**
 * La classe InformationGainTest contiene i test relativi alla classe InformationGain.
 * In particolare, il test verifica che il guadagno di informazione per diversi attributi (mediaVoti, mediaOreDiStudio, mediaAttivita)
 * venga calcolato correttamente.
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */
public class InformationGainTest {

    /**
     * Test per verificare il calcolo del guadagno di informazione per vari attributi degli studenti.
     * Il test controlla che il guadagno di informazione per ciascun attributo sia positivo.
     * Inoltre, vengono stampati i valori del guadagno di informazione per ogni attributo.
     *
     * @throws Exception se si verifica un errore durante il calcolo del guadagno di informazione
     */
    @Test
    void testCalcolaInformationGain() {
        // Genera il dataset di studenti
        List<Studente> studenti = GenerazioneDataSet.generaStudenti();

        // Calcola il guadagno di informazione per ciascun attributo
        double gainMediaVoti = InformationGain.calcolaInformationGain(studenti, "mediaVoti");
        double gainMediaOreDiStudio = InformationGain.calcolaInformationGain(studenti, "mediaOreDiStudio");
        double gainMediaAttivita = InformationGain.calcolaInformationGain(studenti, "mediaAttivita");

        // Verifica i risultati (esempio di aspettative ipotetiche, i valori reali dipendono dai dati)
        assertTrue(gainMediaVoti >= 0, "Il guadagno di informazione per mediaVoti dovrebbe essere positivo");
        assertTrue(gainMediaOreDiStudio >= 0, "Il guadagno di informazione per mediaOreDiStudio dovrebbe essere positivo");
        assertTrue(gainMediaAttivita >= 0, "Il guadagno di informazione per mediaAttivita dovrebbe essere positivo");

        // Per una verifica più precisa, puoi aggiungere un controllo sui valori attesi di gain.
        // I valori dipenderanno dai dati specifici del dataset che stai utilizzando.
        System.out.println("Guadagno di informazione per mediaVoti: " + gainMediaVoti*10);
        System.out.println("Guadagno di informazione per mediaOreDiStudio: " + gainMediaOreDiStudio*10);
        System.out.println("Guadagno di informazione per mediaAttivita: " + gainMediaAttivita*10);
    }
}
