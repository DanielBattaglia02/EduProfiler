EduProfiler - Un progetto di Battaglia Daniel e Pennarella Fabio

EduProfiler è un applicativo software sviluppato con l'obiettivo di calcolare l'indice accademico di uno studente universitario utilizzando tecniche di intelligenza artificiale. L'indice accademico rappresenta un parametro che misura l'efficienza e le performance accademiche di uno studente e viene suddiviso in tre categorie: basso, medio e alto.

Per calcolare l'indice accademico, l'algoritmo si basa sulle seguenti caratteristiche personali di uno studente:
1) Media voti degli esami: Espressa in 30esimi.
2) Media di ore di studio settimanali: Un numero intero che rappresenta la media delle ore dedicate allo studio ogni settimana.
3) Numero di attività universitarie extra: Un intero che indica quante attività extracurriculari lo studente ha svolto durante il suo percorso accademico.

L'algoritmo utilizza un dataset predefinito composto da 27 studenti, ognuno con le sue caratteristiche e un indice accademico associato (basso, medio, alto). Il dataset è stato bilanciato in modo che ci sia lo stesso numero di studenti per ciascuna delle tre classi dell'indice accademico, permettendo così all'algoritmo di allenarsi su dati equilibrati e veritieri.
Il compito dell'algoritmo è predire l'indice accademico di uno studente che ha già inserito tutte le caratteristiche, ad eccezione dell'indice accademico stesso. Quest'ultimo verrà calcolato automaticamente dal sistema, che, grazie all'intelligenza artificiale, apprende dal dataset e fornisce la classificazione corretta.

'algoritmo di previsione è fondato su un albero decisionale, un approccio robusto che consente di classificare gli studenti in base ai loro dati, prevedendo con precisione il loro indice accademico. Questa metodologia offre una chiara interpretabilità, permettendo di comprendere il processo decisionale alla base delle predizioni.

Per migliorare la generalizzazione del modello e garantire una valutazione accurata delle sue performance, viene impiegata la K-Fold Cross Validation, una tecnica di validazione che suddivide il dataset in più sottoinsiemi (folds), alternando l'uso di ciascun sottoinsieme come set di test, mentre gli altri vengono utilizzati per l'addestramento. Questa procedura consente di ottenere una stima più affidabile dell'accuratezza del modello.

Inoltre, l'algoritmo beneficia dell'Information Gain, una metrica fondamentale per la costruzione dell'albero decisionale. L'Information Gain misura la capacità di un attributo di ridurre l'incertezza rispetto alla classe da prevedere, ottimizzando la scelta degli attributi su cui basare le suddivisioni nei nodi dell'albero. Tale approccio contribuisce ad un modello di classificazione più efficiente e preciso.

-- RISORSE --

Il progetto è suddiviso in diversi pacchetti, ognuno con una funzione specifica:
1.	Package "application": Contiene le classi principali che gestiscono l'esecuzione di EduProfiler senza interfaccia grafica.
2.	Package "core": Contiene le classi fondamentali che rappresentano lo Studente e altre entità principali nel calcolo dell'indice accademico.
3.	Package "ml": Include le classi che implementano l'algoritmo di machine learning, in particolare l'albero decisionale per la predizione dell'indice accademico.
4.	Package "utils": Contiene classi di supporto per la generazione del dataset e per la gestione degli eventi nell'interfaccia grafica (listener).
5.	Modulo "test": Comprende le classi di test per verificare il corretto funzionamento delle funzionalità di machine learning del programma.
6.	Directory "lib": Contiene le librerie JUnit necessarie per testare le classi del progetto. È necessario importarle tramite Project Structure in IntelliJ.

-- ESECUZIONE --

L'applicazione può essere eseguita in due modalità:
1.	EduProfiler (versione senza interfaccia grafica): questa versione consente di interagire con il sistema tramite la console. L'utente può inserire i dati di uno studente e ottenere una predizione dell'indice accademico direttamente nel terminale.
2.	EduProfilerGUI (versione con interfaccia grafica): Questa versione, che include una GUI, offre un'esperienza più interattiva e visuale. Gli utenti possono inserire i dati dello studente attraverso moduli grafici, e l'applicazione mostrerà la predizione dell'indice accademico direttamente nell'interfaccia utente.
In entrambe le modalità, l'algoritmo di machine learning viene utilizzato per calcolare e restituire l'indice accademico predetto per ogni studente.

