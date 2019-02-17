# Gantt PPTX Recap

Scopo del progetto è di generare delle slide PPTX di recap dello stato di un progetto.

Idealmente, il progetto riceve in input un json/md, quindi genera un powerpoint che rappresenta lo stato.

## Compilazione ed esecuzione

Il progetto è sviluppato con Java8 + maven.

Posizionarsi nella root, quindi eseguire:

```bash
mvn clean package
```

Nella cartella target verrà generato `gantt-pptx-recap-x.y.z-SNAPSHOT.jar`.

Eseguire l'applicazione compilata:

```bash
java -jar target\gantt-pptx-recap-0.0.1-SNAPSHOT.jar
```

## Fase 1

I dati rappresentati saranno:

- Data di partenza
- Data di fine
- Elenco di task, rappresentati da:
    - Nome
    - Data inizio
    - Data fine

## Sviluppi futuri

- Dipendenza tra task
- Owner task
- Note per task