# Gantt PPTX Recap

Scopo del progetto è di generare delle slide PPTX di recap dello stato di un progetto.

Idealmente, il progetto riceve in input un json/md, quindi genera un powerpoint che rappresenta lo stato.

## Compilazione ed esecuzione

Il progetto è sviluppato con Java8 + maven + Lombok (1.18.6).

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

- [X] Data di partenza
- [X] Data di fine
- [X] Elenco di task, rappresentati da:
	- Nome
	- Data inizio
	- Data fine
- [X] creare presentazione in base a file di input
- [X] rendere configurabile l'applicazione
- [X] possibilità di inserire più task su una stessa riga
- [ ] possibilità di inserire delle note per un task, che compariranno accanto
