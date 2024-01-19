# Spring Book Web

## Descrizione del Progetto

Questo progetto è una piattaforma web basata su Spring Boot che consente agli utenti di gestire una lista di libri. L'applicazione offre diverse funzionalità, tra cui la registrazione degli utenti, l'accesso, la gestione dei libri, la modifica dei dati dell'utente, la gestione dei preferiti e l'interazione con un'API esterna per caricare dati aggiuntivi nel database.

## Funzionalità Principali

### 1. Registrazione e Login

Gli utenti possono registrarsi fornendo le proprie informazioni, come nome, cognome, indirizzo email e password. Dopo la registrazione, possono effettuare il login utilizzando le credenziali fornite.

### 2. Gestione dei Libri

Gli utenti possono visualizzare una lista di libri disponibili, aggiungere nuovi libri specificando titolo, autore, data di pubblicazione, descrizione e prezzo. Possono anche modificare e rimuovere i libri dalla loro lista personale.

### 3. Preferiti

Gli utenti hanno la possibilità di aggiungere i libri ai preferiti per accedervi facilmente in futuro. Possono anche rimuovere i libri dalla lista dei preferiti.

### 4. Visualizzazione Dettagli

Gli utenti possono visualizzare i dettagli completi di ciascun libro, inclusi titolo, autore, data di pubblicazione, descrizione e prezzo.

### 5. Modifica Dati Utente

Gli utenti possono modificare le proprie informazioni personali, come nome, cognome ed email. Possono anche cambiare la password.

### 6. Rimozione Utente

Gli utenti hanno la possibilità di eliminare definitivamente il proprio account.

### 7. Interazione con API Esterna

Gli utenti possono sincronizzare la propria lista di libri con un'API esterna, consentendo loro di aggiungere rapidamente nuovi libri dal servizio di terze parti. Inoltre, possono rimuovere tutti i libri dal database.

## Istruzioni per l'Installazione

###1. Clonare il repository:

```bash
git clone https://github.com/giovanni-ercolano/SpringBookWeb.git
```

### 2. Importare il progetto in un IDE (IntelliJ, Eclipse, ecc.).

### 3. Configurare le informazioni del database nel file application.properties.

### 4. Eseguire l'applicazione.

### 5. Accedere all'applicazione tramite un browser all'indirizzo http://localhost:8080.

## Tecnologie Utilizzate
- Spring Boot
- Spring Security
- Thymeleaf (Template Engine)
- Hibernate (ORM)
- MySQL (o database di tua scelta)

#Nota: Assicurati di avere Java e Maven installati sul tuo sistema prima di eseguire l'applicazione.
