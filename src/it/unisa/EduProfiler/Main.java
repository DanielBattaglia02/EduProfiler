package it.unisa.EduProfiler;

import java.util.List;
import java.util.Scanner;

/**
 * La classe Main è il punto di ingresso per l'applicazione, che consente all'utente di interagire con un albero decisionale
 * per predire l'indice accademico di uno studente. L'utente può inserire nuovi studenti e ottenere una predizione basata su
 * un albero decisionale costruito con dati di studenti predefiniti.
 *
 * @author Battaglia Daniel
 * @author Pennarella Fabio
 */
public class Main {

    /**
     * Il metodo principale esegue l'interazione con l'utente, permettendo di inserire un nuovo studente e di predire
     * l'indice accademico tramite l'albero decisionale costruito.
     *
     * @param args gli argomenti passati al programma (non utilizzati in questo caso)
     */
    public static void main(String[] args) {
        // Genera i 50 studenti manualmente
        List<Studente> studenti = GenerazioneDataSet.generaStudenti();

        // Costruisci l'albero decisionale
        Nodo albero = AlberoDecisionale.costruisciAlbero(studenti);

        // Crea un oggetto Scanner per l'input dell'utente
        Scanner scanner = new Scanner(System.in);

        // Ciclo principale per l'interazione con l'utente
        while (true) {
            // Mostra il menu con solo due opzioni
            System.out.println("\nMenu:");
            System.out.println("1. Inserisci un nuovo studente");
            System.out.println("2. Esci");

            System.out.print("Scegli un'opzione (1-2): ");
            int scelta = scanner.nextInt();

            if (scelta == 2) {
                System.out.println("Uscita in corso...");
                break; // Esci dal ciclo
            }

            switch (scelta) {
                case 1:
                    // Inserisci i dati del nuovo studente
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

        // Chiudi lo scanner dopo l'uso
        scanner.close();
    }

    /**
     * Metodo per inserire un nuovo studente tramite input dell'utente. L'utente fornisce la media dei voti,
     * le ore di studio e le attività extra, mentre l'indice accademico verrà predetto dal modello.
     *
     * @param scanner lo scanner utilizzato per acquisire l'input dell'utente
     * @return un oggetto {@link Studente} contenente i dati inseriti dall'utente
     */
    private static Studente inserisciStudente(Scanner scanner) {
        int mediaVoti;
        while (true) {
            System.out.print("Inserisci la media voti (compresa tra 18 e 30): ");
            mediaVoti = scanner.nextInt();

            // Controllo del valore di mediaVoti
            if (mediaVoti >= 18 && mediaVoti <= 30) {
                break; // Esci dal ciclo se il valore è valido
            } else {
                System.out.println("Errore: la media voti deve essere compresa tra 18 e 30. Riprova.");
            }
        }

        System.out.print("Inserisci la media ore di studio: ");
        int mediaOreDiStudio = scanner.nextInt();

        System.out.print("Inserisci la media attività extra: ");
        int mediaAttivitaExtra = scanner.nextInt();

        // Crea e restituisce un nuovo studente con i dati inseriti
        return new Studente(mediaVoti, mediaOreDiStudio, mediaAttivitaExtra, 0); // Indice accademico impostato a 0 (verrà predetto)
    }
}
