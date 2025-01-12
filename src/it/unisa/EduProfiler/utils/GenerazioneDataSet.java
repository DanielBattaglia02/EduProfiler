package it.unisa.EduProfiler.utils;

import it.unisa.EduProfiler.core.Studente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * La classe GenerazioneDataSet fornisce un metodo per generare manualmente un dataset di studenti.
 * Ogni studente è rappresentato da una serie di attributi come la media dei voti, le ore di studio settimanali e le attività extra.
 * Questi dati vengono usati per costruire un albero decisionale per la previsione dell'indice accademico.
 *
 * @author Battaglia Daniel
c
 */
public class GenerazioneDataSet {

    /**
     * Metodo per generare manualmente una lista di 27 studenti con vari attributi.
     * Ogni studente è creato con valori specifici per mediaVoti, mediaOreDiStudio e mediaAttività.
     *
     * @return una lista di 27 studenti con valori predefiniti
     */
    public static List<Studente> generaStudenti() {
        List<Studente> studenti = new ArrayList<>();

        // Aggiungi 27 nuovi studenti seguendo le combinazioni
        // Studente 1
        studenti.add(new Studente(18, 3, 2, 1));  //bassa-bassa-bassa
        // Studente 2
        studenti.add(new Studente(19, 4, 3, 1));  //bassa-bassa-media
        // Studente 3
        studenti.add(new Studente(18, 5, 7, 1));  //bassa-bassa-alta
        // Studente 4
        studenti.add(new Studente(21, 15, 2, 1));  //bassa-media-bassa
        // Studente 5
        studenti.add(new Studente(21, 18, 4, 1));  //bassa-media-media
        // Studente 6
        studenti.add(new Studente(19, 20, 6, 1));  //bassa-media-alta
        // Studente 7
        studenti.add(new Studente(20, 21, 1, 1));  //bassa-alta-bassa
        // Studente 8
        studenti.add(new Studente(20, 21, 5, 1));  //bassa-alta-media
        // Studente 9
        studenti.add(new Studente(22, 21, 9, 1));  //bassa-alta-alta

        // Studente 10
        studenti.add(new Studente(25, 4, 2, 1));  //media-bassa-bassa
        // Studente 11
        studenti.add(new Studente(25, 5, 4, 1));  //media-bassa-media
        // Studente 12
        studenti.add(new Studente(25, 5, 8, 1));  //media-bassa-alta
        // Studente 13
        studenti.add(new Studente(25, 19, 2, 1));  //media-media-bassa
        // Studente 14
        studenti.add(new Studente(25, 20, 5, 1));  //media-media-media
        // Studente 15
        studenti.add(new Studente(25, 18, 6, 1));  //media-media-alta
        // Studente 16
        studenti.add(new Studente(25, 23, 2, 1));  //media-alta-bassa
        // Studente 17
        studenti.add(new Studente(25, 24, 5, 1));  //media-alta-media
        // Studente 18
        studenti.add(new Studente(25, 21, 9, 1));  //media-alta-alta

        // Studente 19
        studenti.add(new Studente(30, 2, 2, 1));  //alta-bassa-bassa
        // Studente 20
        studenti.add(new Studente(29, 3, 4, 1));  //alta-bassa-media
        // Studente 21
        studenti.add(new Studente(30, 6, 10, 1));  //alta-bassa-alta
        // Studente 22
        studenti.add(new Studente(27, 17, 2, 1));  //alta-media-bassa
        // Studente 23
        studenti.add(new Studente(28, 20, 3, 1));  //alta-media-media
        // Studente 24
        studenti.add(new Studente(29, 18, 7, 1));  //alta-media-alta
        // Studente 25
        studenti.add(new Studente(30, 21, 2, 1));  //alta-alta-bassa
        // Studente 26
        studenti.add(new Studente(30, 25, 5, 1));  //alta-alta-media
        // Studente 27
        studenti.add(new Studente(30, 28, 12, 1));  //alta-alta-alta

        return studenti;
    }
}