# OOS Projektni
## Specifikacija aplikacije 
Jednostavna <i>shell</i> aplikacija korisniku omogućava interakciju sa fajlsistemom. Ova aplikacija radi isljučivo na Linux operativnom sistemu i podržava sljedeće komande:

1. <b>login</b> - prijavljuje korisnika u aplikaciju i potom mu dodjeljuje odgovarajući direktorij.

2. <b>where</b> - prikazuje trenutnu lokaciju korisnika na fajlsistemu.

3. <b>go</b> <i>putanja</i> - pozicionira korisnika na putanju naveden kao argument.

4. <b>create</b> [-d] <i>putanja</i> - na zadatoj putanji kreira datoteku, a ako se navede parametar -d, kreira folder.

5. <b>list</b> [<i>putanja</i>] - formatirano ispisuje stablo direktorijuma zadato kao argument, (tekućeg direktorijuma ako se ne navede argument).

6. <b>print</b> <i>datoteka</i> - ispisuje sadržaj tekstualne datoteke na konzolu. U slučaju navođenja netekstualne datoteke ispisuje se greška

7. <b>find</b> <i>"tekst" datoteka</i> - vraća broj linije na kojoj je pronađen zadati tekst u datoteci (prvo pojavljivanje teksta). Tekst je uvijek jednolinijski.

8. <b>findDat</b> <i>datoteka putanja</i> - pretražuje datoteku u zadatoj putanji i kao izlaz vraća lokaciju iste.
