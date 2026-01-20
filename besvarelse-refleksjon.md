# Besvarelse på refleksjonspørsmålene

Ditt brukernavn på Oslomet: masta5802@oslomet.no

## Refleksjonsspørsmål Oppgave 1 

S1: Hva er fordelene med å lagre data i et slikt format (CSV - Comma Separated Values)?
- Ditt svar: Noen av fordelene med csv er at det er universell kompatabilitet og enkelhet ved dataoverføring, siden det lagrer data i rent tekstformat.
  
S2: Hva skjer hvis et av feltene, for eksempel et navn, inneholder et komma? Hvilke problemer skaper det for din parsing-logikk?
- Ditt svar: Hvis et felt inneholder et komma, vil parsing som splitter på komma tolke det som et nytt felt og ødelegge strukturen (feil antall kolonner).

S3: Beregning av lagringsbehov

S3.1: La oss lage en forenklet modell for vår fil. Anta at hvert tegn (character) er 1 byte (dette er en forenkling, f.eks. husk UTF-8). Regn ut den omtrentlige størrelsen i bytes for én linje i din fil studenter.csv (f.eks., en linje er 101,Mickey,CS). Ikke glem å telle med kommaene og et tegn for linjeskift.
- Ditt svar: ca 14 bytes per linje

S3.2 Basert på denne beregningen, hva ville den teoretiske filstørrelsen vært for 1 million studenter? Hvor stor for 1 milliard studenter? Uttrykk svarene i MB, GB eller TB.
- Ditt svar: for 1 million studenter ville det vært ca 14MB, og for 1 milliard, 14GB.

## Refleksjonsspørsmål Oppgave 2

S1: Sammenlign tidene fra de tre testene (liten fil, tidlig treff i stor fil, og sent treff i stor fil). Hva forteller dette deg om ytelsen til en lineær skanning?
- Ditt svar: tiden øker proporsjonalt med antall linjer som må leses. Lineær scanning er avhening av hvor langt ut i filen treffet ligger.
  
S2: Hvilken rolle spiller **BufferedWriter** (en klasse i Java standard I/O biblioteket), hvis den brukes i datageneratoren? Forklar konsepter fra forelesningen, som IO Blocks og bufring.
- Ditt svar: BufferedWriter samler mange små skriveoperasjoner i minnet og skriver dem til disk i større I/O-blokker i stedet for en og en.
  Det reduserer antall dyre disk-operasjoner og gir mye bedre ytelse, som er selve poenget med bufring i I/O.


S3: Teoretisk lesetid:  

S3.1 Hva blir den faktiske filstørrelsen hvis det er 1 millioner rader i filen med brukerdata (format spesifisert i Oppgave 2.1).
- Ditt svar: ca 53MB
  
S3.2 Bruk tabellen i sliden *“Lagringsmedier – fra rask til billig”* fra forelesningen. Hva ville den teoretiske tiden vært for å lese hele denne filen (en full "scan") fra henholdsvis en **HDD (Hard Disk)** og en **High-end SSD**?
- Ditt svar: Hvis filstørrelsen er 53mb vil det ta omtrent 10 millisekunder med HDD, og omtrent 10 mikrosekunder med SSD, da SSD er 1000x raskere enn HDD.

S3.3 Anta at følgende formel gjelder: 
```
Total Time \= AccessLatency \* M \+ DataSize/ScanThroughput 
```
For en full, sekvensiell skanning kan vi anta at M=1 (dataene leses som én stor, sammenhengende blokk). Sammenlign de teoretiske tidene med den faktiske tiden du målte i Oppgave 2.3 (Stor fil, sent treff). Hvorfor kan tidene være forskjellige? (Hint: Tenk på operativsystemets fil-caching, CPU-bruk for parsing i Java, etc.).
- Ditt svar: De faktiske tidene er mye kortere enn den teoretiske tiden fordi operativsystemet ofte cacher filen i minnet, og Java må bare lese fra cache i stedet for fra disk. I tillegg kan parsing i Java påvirke tiden minimalt, men for små målinger blir disktilgang ikke en begrensing.

## Refleksjonsspørsmål Oppgave 3

S1: Hvorfor er det mer effektivt å lese **studenter.csv** og **kurs.csv** inn i HashMap først, i stedet for å søke gjennom filene for hver eneste linje i **paameldinger.csv**?
- Ditt svar: fordi hashmaps gir konstant tid (O(1)) for oppslag, mye raskere enn å skanne filene sekvensielt (O(n)) for hver påmelding.


S2: Lagringsplass og minnebruk:

La oss si vi har 1 million studenter, 1000 kurs, og hver student tar i gjennomsnitt 5 kurs (totalt 5 millioner påmeldinger).

**Scenario A (Ikke-normalisert):** Vi lagrer \`studentID, fornavn, etternavn, kursID, kursnavn\` på hver linje. Anta at studentinfo trenger 100 bytes og kursinfo trenger 50 bytes for lagring. Hva blir den totale filstørrelsen for de 5 millioner påmeldingene?
- Ditt svar:  ikke-normalisert: 150B per rad * 5M rader = 750 MB. Normalisert: 

**Scenario B (Normalisert):** Vi har tre filer. Beregn den totale størrelsen for **studenter.csv** (1M rader), **kurs.csv** (1000 rader), og **paameldinger.csv** (5M rader, inneholder kun to ID-er, f.eks. 8 bytes totalt per rad).  
Sammenlign total lagringsplass i Scenario A og B. Hvor mye plass sparer vi på normalisering?
- Ditt svar: 1M studenter * 100B = 100 MB
  1000 kurs * 50B = 50 KB (tilnærmet 0MB)
  5M påmeldinger * 8B = 40 MB
  Totalt ≈ 140 MB, så vi sparer ca. 610 MB

S3: I din Java-løsning lastet du data inn i RAM. Se på tabellen i sliden *“Lagringsmedier – fra rask til billig”*. Hva er den typiske kostnaden per TB for RAM sammenlignet med SSD og HDD? Hvorfor er det en viktig vurdering om en datastruktur (som din HashMap-indeks) passer i RAM eller ikke?
- Ditt svar: Typisk kostnad for RAM per TB er ca 3125$, for SSD er det ca 100$ og for HDD er det ca 25$. Det er viktig fordi datastrukturer i RAM gir ekstremt rask tilgang, mens hvis de ikke får plass må man lese fra disk hele tiden,og det blir veldig tregt.

## Refleksjonsspørsmål Oppgave 4 

S1: Hva er den observerte tidsforskjellen mellom det lineære søket og det indekserte oppslaget? Hvorfor er forskjellen så enorm?
- Ditt svar: Indeksert oppslag er mye raskere fordi vi hopper direkte til linjen via posisjon i filen, mens lineær søk må lese alle linjene sekvensielt. Forskjellen er på ca 520ms.
- 
S2: Hvilken lagringstype (RAM, SSD, HDD) opererer HashMap\-oppslaget på, og hva er den tilhørende “Aksesstid” ifølge tabellen fra forelesningene i sliden *“Lagringsmedier – fra rask til billig”*?
- Ditt svar:HashMap-operasjoner skjer i RAM, med nanosekund-aksestid (~50–100 ns)
- 
Kostnaden ved en indeks:  
S3: Hvor stor ble din brukere.idx\-fil? Sammenlign størrelsen med brukere.csv.
- Ditt svar: Indeksfilen brukere.idx er ca 36MB, mens datafilen brukere.csv er ca 47MB. Indeksen er litt mindre enn selve datafilen, fordi den kun lagrer epost og posisjon, ikke hele brukerdataene.
  
S4: En indeks er ikke gratis, den tar opp ekstra lagringsplass. Hvis indeksen din var 200 MB, hva ville den kostet å lagre den på en HDD? Hva med en SSD? Bruk tallene fra forelesningen, slide *“Lagringsmedier – fra rask til billig”*. Er dette en akseptabel kostnad for den ytelsesgevinsten du får? (Hint: *“tid/plass trade-off”*)
- Ditt svar: for HDD: 0,0002 TB*25$=0,005 og for SSD: 0,0002 TB*100$=0,02$. Fordelen med ekstremt raskt oppslag (1ms vs. hundrevis av ms) gjør at tid/plass trade-off er mer enn verdt det.

## Vedlegg Lagringshierarki: Hastighet, kostnad og kapasitet
Tabellen er relevant for å besvare flere av refleksjonsspørsmålene. Tabellen er kopiert fra `https://cs145-bigdata.web.app/Section2-Systems/storage-paging.html`.
|Storage Level 	|Access Latency 	|Throughput 	|Cost per TB 	|Typical Capacity 	|Use Case|
|-|-|-|-|-|-|
|CPU Registers 	|1 cycle 	|- 	|- 	|< 1KB 	|Immediate values|
|L1/L2 Cache 	|1-10ns 	|- 	|- 	|64KB - 8MB 	|Hot instructions|
|RAM (Buffer Pool) 	|100ns 	|100 GB/s 	|$3,500 	|16GB 	|Working set pages|
|SSD 	|10μs 	|5 GB/s 	|$75 	|512GB 	|Active tables|
|HDD 	|10ms 	|100 MB/s 	|$25 	|4TB 	|Cold storage|
|Network Storage 	|1μs 	|10 GB/s 	|Variable 	|∞ 	|Distributed cache|

SLUTT.
