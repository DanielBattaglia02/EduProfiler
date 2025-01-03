package it.unisa.EduProfiler.application;

import it.unisa.EduProfiler.utils.EduProfilerActionListener;
import it.unisa.EduProfiler.core.Studente;
import it.unisa.EduProfiler.ml.AlberoDecisionale;
import it.unisa.EduProfiler.ml.Nodo;
import it.unisa.EduProfiler.utils.GenerazioneDataSet;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * La classe EduProfilerGUI rappresenta un'opzione per eseguire l'applicazione EduProfiler con una GUI associata.
 * Si occupa di creare l'interfaccia grafica dell'applicazione per predire l'indice accademico
 * di uno studente basandosi sui dati forniti.
 * <p>
 * Struttura dell'interfaccia:
 * - Un logo nella parte superiore.
 * - Campi di input per inserire la media voti, le ore di studio settimanali e il numero di attività extra-curriculari.
 * - Un pulsante per avviare la predizione.
 * - Un'area di testo per visualizzare i risultati o eventuali messaggi di errore.
 * </p>
 *
 * @author Battaglia Daniel
 */
public class EduProfilerGUI {

    /**
     * Metodo principale dell'applicazione.
     * Si occupa di creare il Jframe con tutte le sue componenenti, oltre che inizializzare il modello decisionale
     * per la predizione dell'indice accademico e generare il dataset.
     *
     * @param args argomenti della riga di comando (non utilizzati).
     */
    public static void main(String[] args) {

        /***********************
         * jpanel top
         *      logo
         * jpanel main
         *      3 jlabel
         *      3 jtextField
         *      1 bottone
         *          1 actionListener
         ************************/

        final int FRAME_WIDTH = 500;
        final int FRAME_HEIGHT = 600;

        // Genera i 27 studenti manualmente
        List<Studente> studenti = GenerazioneDataSet.generaStudenti();

        // Costruisci l'albero decisionale
        Nodo albero = AlberoDecisionale.costruisciAlbero(studenti);

        // Creazione del frame
        JFrame frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("EduProfiler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0, 0);

        // Imposta l'immagine come icona del frame (logo dell'applicazione)
        ImageIcon appLogo = new ImageIcon("img/logo1.png");
        Image scaledLogoImage = appLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);
        frame.setIconImage(appLogo.getImage());

        // Creazione di un pannello superiore per il logo
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Spazio in alto
        frame.add(topPanel, BorderLayout.NORTH);

        // Creazione del pannello principale con BoxLayout per layout verticale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.add(mainPanel, BorderLayout.CENTER);

        // Aggiunta del logo in alto a destra
        JLabel logoLabel = new JLabel(scaledLogoIcon);
        topPanel.add(logoLabel, BorderLayout.CENTER);

        // Creazione dei componenti
        JLabel titolo = new JLabel("EduProfiler - Predizione dell'indice accademico di uno studente");
        titolo.setFont(new Font("Arial", Font.BOLD, 18)); // Ingrandisci il testo del titolo
        titolo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelMediaVoti = new JLabel("Media voti:");
        jLabelMediaVoti.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField mediaVoti = new JTextField(10);
        mediaVoti.setMaximumSize(new Dimension(200, 25)); // Imposta una dimensione massima

        JLabel jLabelMediaOreDiStudio = new JLabel("Media ore di studio settimanali:");
        jLabelMediaOreDiStudio.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField mediaOreDiStudio = new JTextField(10);
        mediaOreDiStudio.setMaximumSize(new Dimension(200, 25));

        JLabel jLabelAttivitaExtra = new JLabel("Numero attività extra-curriculari:");
        jLabelAttivitaExtra.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField attivitaExtra = new JTextField(10);
        attivitaExtra.setMaximumSize(new Dimension(200, 25));

        JButton btnInserisci = new JButton("Predici inidice accedemico");
        btnInserisci.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Casella di testo per mostrare un messaggio
        JTextArea messageArea = new JTextArea(5, 20);
        messageArea.setMaximumSize(new Dimension(400, 100));
        messageArea.setEditable(false); // Rende la JTextArea non modificabile
        messageArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Associa un'istanza del listener al pulsante
        btnInserisci.addActionListener(new EduProfilerActionListener(albero, mediaVoti, mediaOreDiStudio, attivitaExtra, messageArea));

        // Aggiunta dei componenti al pannello principale
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spaziatura
        mainPanel.add(titolo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(jLabelMediaVoti);
        mainPanel.add(mediaVoti);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(jLabelMediaOreDiStudio);
        mainPanel.add(mediaOreDiStudio);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(jLabelAttivitaExtra);
        mainPanel.add(attivitaExtra);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnInserisci);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(messageArea);

        // Mostra il frame
        frame.setVisible(true);
    }
}