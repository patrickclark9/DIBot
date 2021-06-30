# DIBot - An Easy-Access information Bot for the Computer Science web site of the University of Bari

**Cos'è DIBot**

DIBot è un bot per la piattaforma di messaggistica Telegram che fornisce accesso rapido alle informazioni, le notizie e gli eventi del Dipartimento di Informatica dell'Università degli Studi di Bari.
****
**Strumenti di Sviluppo**

Il bot è stato sviluppato utilizzando il Wrapper in linguaggio Java di Telegram bot API, utilizzando principalmente l'IDE IntelliJ IDEA per stesura di codice, testing e debugging. Per la gestione delle Dependencies Java e per il Building del progetto è stato utilizzato Maven vers. 3.8.1 (https://maven.apache.org/) Per il controllo di versione è stato utilizzato Git, con repository remota su GitHub (GitHub: https://github.com/
Repository Bot: https://github.com/patrickclark9/DIBot). Come database è stato utilizzato il Database orientato ai Documenti MongoDB, con cluster hostato su un server Azure, e gestito tramite l'applicazione GUI MongoDB Compass. Si è optato per esso per facilità d'uso, velocità nella gestione delle Query e ampia documentazione e supporto. Per il deploy invece, si è sfruttato Docker, e l'applicazione è stata inserita all'interno di un Container, sfruttando come Docker Image un'immagine Ubuntu.
Per la stesura della documentazione e la gestione dei container è stato utilizzato l'editor Visual Studio Code.
****
**Come funziona**

All'avvio, il bot si connette al Cluster di MongoDB dov'è hostato il Database contente le informazioni ottenute attraverso lo Scraper (https://github.com/mariogiordano1/Scraper), sviluppato ad hoc per ottenere con un approccio dinamico tutte le informazioni disponibili sulla Homepage del sito del Dipartimento di Informatica (https://www.uniba.it/ricerca/dipartimenti/informatica).

Con il comando "/start", l'utente avvia la conversazione con il bot. Il comando apre una tastiera personalizzata con tutte le opzioni disponibili per la conversazione con il bot. Premuto un pulsante della tastiera, il programma effettua una query sul database in base alla scelta dell'utente, e restituisce le informazioni richieste da quest'ultimo in formato testuale, con annessi eventuali collegamenti ipertestuali alle pagine di riferimento.

Poichè l'operazione di Scraping potrebbe non andare a buon fine, per esempio a seguito di un aggiornamento sostanziale al codice HTML del sito web, oppure a seguito di un malfunzionamento del server host dello Scraper, è stato introdotto un pulsante di segnalazione di errore, con annesse mail e pagina Issue di GitHub per la segnalazione dell'errore.




