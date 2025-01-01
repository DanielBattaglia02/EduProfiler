package it.unisa.EduProfiler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe EduProfilerActionListener implementa un ActionListener per gestire gli eventi associati al pulsante
 * nell'applicazione EduProfilerGUI. Si occupa di validare i dati di input forniti dall'utente, creare un'istanza
 * di studente e predire il suo indice accademico utilizzando un albero decisionale.
 * <p>
 * Funzionalità principali:
 * - Validazione dei dati di input.
 * - Predizione dell'indice accademico tramite un modello ad albero decisionale.
 * - Mostra messaggi di successo o errore nell'interfaccia utente.
 * </p>
 *
 * @author Battaglia Daniel
 */
public class EduProfilerActionListener implements ActionListener {

    private final Nodo albero;
    private final JTextField mediaVoti;
    private final JTextField mediaOreDiStudio;
    private final JTextField attivitaExtra;
    private final JTextArea messageArea;

    /**
     * Costruttore della classe EduProfilerActionListener.
     *
     * @param albero il modello decisionale (albero) utilizzato per la predizione.
     * @param mediaVoti JTextField per l'inserimento della media dei voti dello studente.
     * @param mediaOreDiStudio JTextField per l'inserimento della media delle ore di studio settimanali.
     * @param attivitaExtra JTextField per l'inserimento del numero di attività extra-curriculari.
     * @param messageArea JTextArea per mostrare messaggi di output o errori.
     */
    public EduProfilerActionListener(Nodo albero, JTextField mediaVoti, JTextField mediaOreDiStudio, JTextField attivitaExtra, JTextArea messageArea) {
        this.albero = albero;
        this.mediaVoti = mediaVoti;
        this.mediaOreDiStudio = mediaOreDiStudio;
        this.attivitaExtra = attivitaExtra;
        this.messageArea = messageArea;
    }

    /**
     * Metodo invocato quando viene premuto il pulsante associato a questo listener.
     * Esegue la validazione degli input, predice l'indice accademico dello studente e
     * aggiorna l'interfaccia utente con i risultati o eventuali messaggi di errore.
     *
     * @param e l'evento generato dal pulsante.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Controlla che i campi siano validi
            double mediaVotiValue = validateDoubleInput(mediaVoti.getText(), "Media voti");
            double mediaOreValue = validateDoubleInput(mediaOreDiStudio.getText(), "Media ore di studio");
            int attivitaValue = validateIntegerInput(attivitaExtra.getText(), "Attività extra-curriculari");

            // Crea un nuovo studente
            Studente nuovoStudente = new Studente(mediaVotiValue, mediaOreValue, attivitaValue, 0);

            // Predici l'indice accademico utilizzando l'albero
            String indicePredetto = AlberoDecisionale.prediciIndice(albero, nuovoStudente);

            // Mostra i dati inseriti e l'indice predetto
            messageArea.setText("Dati inseriti:\n"
                    + "Media voti: " + mediaVotiValue + "\n"
                    + "Media ore di studio: " + mediaOreValue + "\n"
                    + "Attività extra-curriculari: " + attivitaValue + "\n"
                    + "Indice Accademico predetto: " + indicePredetto);
        } catch (IllegalArgumentException ex) {
            // Mostra il messaggio di errore nella messageArea
            messageArea.setText("Errore: " + ex.getMessage());
        } catch (Exception ex) {
            // Gestisce errori imprevisti
            messageArea.setText("Errore imprevisto: " + ex.getMessage());
        }
    }

    /**
     * Metodo per validare l'input come numero decimale.
     *
     * @param input il valore inserito dall'utente.
     * @param fieldName il nome del campo da validare (utilizzato nei messaggi di errore).
     * @return il valore convertito in double se valido.
     * @throws IllegalArgumentException se l'input non è un numero decimale valido.
     */
    private double validateDoubleInput(String input, String fieldName) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(fieldName + " deve essere un numero decimale valido.");
        }
    }

    /**
     * Metodo per validare l'input come numero intero.
     *
     * @param input il valore inserito dall'utente.
     * @param fieldName il nome del campo da validare (utilizzato nei messaggi di errore).
     * @return il valore convertito in int se valido.
     * @throws IllegalArgumentException se l'input non è un numero intero valido.
     */
    private int validateIntegerInput(String input, String fieldName) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(fieldName + " deve essere un numero intero valido.");
        }
    }
}