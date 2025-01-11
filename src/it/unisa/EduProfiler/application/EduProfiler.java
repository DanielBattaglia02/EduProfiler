package it.unisa.EduProfiler.application;

import it.unisa.EduProfiler.core.Studente;
import it.unisa.EduProfiler.ml.AlberoDecisionale;
import it.unisa.EduProfiler.ml.Nodo;
import it.unisa.EduProfiler.utils.GenerazioneDataSet;

import java.util.List;
import java.util.Scanner;

/**
 * La classe EduProfiler è il punto di ingresso per l'applicazione, che consente all'utente di interagire con un albero decisionale
 * per predire l'indice accademico di uno studente. L'utente può inserire nuovi studenti e ottenere una predizione basata su
 * un albero decisionale costruito con dati di studenti predefiniti.
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */
public class EduProfiler {

    /**
     * Il metodo principale esegue l'interazione con l'utente, permettendo di inserire un nuovo studente e di predire
     * l'indice accademico tramite l'albero decisionale costruito.
     *
     * @param args gli argomenti passati al programma (non utilizzati in questo caso)
     */
    public static void main(String[] args)
    {
        List<Studente> studenti = GenerazioneDataSet.generaStudenti();

        Nodo albero = AlberoDecisionale.costruisciAlbero(studenti);

        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("\nMenu:");
            System.out.println("1. Inserisci un nuovo studente");
            System.out.println("2. Esci");

            System.out.print("Scegli un'opzione (1-2): ");
            int scelta = scanner.nextInt();

            if (scelta == 2)
            {
                System.out.println("Uscita in corso...");
                break; // Esci dal ciclo
            }

            switch (scelta)
            {
                case 1:
                    Studente nuovoStudente = inserisciStudente(scanner);
                    System.out.println("Nuovo studente aggiunto: " + nuovoStudente);

                    // Predici l'indice accademico per il nuovo studente
                    String indicePredetto = AlberoDecisionale.prediciIndice(albero, nuovoStudente);
                    System.out.println("Indice accademico predetto: " + indicePredetto);
                    break;

                default:
                    System.out.println("Opzione non valida. Riprova.");
                    break;
            }
        }

        scanner.close();
    }

    /**
     * Metodo per inserire un nuovo studente tramite input dell'utente. L'utente fornisce la media dei voti,
     * le ore di studio e le attività extra, mentre l'indice accademico verrà predetto dal modello.
     *
     * @param scanner lo scanner utilizzato per acquisire l'input dell'utente
     * @return un oggetto {@link Studente} contenente i dati inseriti dall'utente
     */
    private static Studente inserisciStudente(Scanner scanner)
    {
        int mediaVoti;

        while (true)
        {
            System.out.print("Inserisci la media voti (compresa tra 18 e 30): ");
            mediaVoti = scanner.nextInt();

            if (mediaVoti >= 18 && mediaVoti <= 30) {
                break; // Esci dal ciclo se il valore è valido
            }
            else
            {
                System.out.println("Errore: la media voti deve essere compresa tra 18 e 30. Riprova.");
            }
        }

        int mediaOreDiStudio;
        while (true)
        {
            System.out.print("Inserisci la media ore di studio (compresa tra 1 e 100): ");
            mediaOreDiStudio = scanner.nextInt();

            if (mediaOreDiStudio >= 1 && mediaOreDiStudio <= 100) {
                break; // Esci dal ciclo se il valore è valido
            }
            else
            {
                System.out.println("Errore: la media ore di studio deve essere compresa tra 1 e 100. Riprova.");
            }
        }

        int mediaAttivitaExtra;
        while (true)
        {
            System.out.print("Inserisci la media attività extra (compresa tra 0 e 100): ");
            mediaAttivitaExtra = scanner.nextInt();

            if (mediaAttivitaExtra >= 0 && mediaAttivitaExtra <= 100) {
                break; // Esci dal ciclo se il valore è valido
            }
            else
            {
                System.out.println("Errore: la media attività extra deve essere compresa tra 0 e 100. Riprova.");
            }
        }

        return new Studente(mediaVoti, mediaOreDiStudio, mediaAttivitaExtra, 1); // Indice accademico impostato a 1 (verrà predetto)
    }
}
