# OOS Projektni
## Specifikacija aplikacije 
Jednostavna <i>shell</i> aplikacija korisniku omogućava interakciju sa fajlsistemom. Ova aplikacija radi isljučivo na Linux operativnom sistemu i podržava sljedeće komande:

* <b>login</b> - prijavljuje korisnika u aplikaciju i potom mu dodjeljuje odgovarajući direktorij. Sve sljedeće komande može unositi isključivo ulogovani korisnik.

* <b>where</b> - prikazuje trenutnu lokaciju korisnika na fajlsistemu.

* <b>go</b> <i>putanja</i> - pozicionira korisnika na putanju naveden kao argument.

* <b>create</b> [-d] <i>putanja</i> - na zadatoj putanji kreira datoteku, a ako se navede parametar -d, kreira folder.

* <b>list</b> [<i>putanja</i>] - formatirano ispisuje stablo direktorijuma zadato kao argument, (tekućeg direktorijuma ako se ne navede argument).

* <b>print</b> <i>datoteka</i> - ispisuje sadržaj tekstualne datoteke na konzolu. U slučaju navođenja netekstualne datoteke ispisuje se greška

* <b>find</b> <i>"tekst" datoteka</i> - vraća broj linije na kojoj je pronađen zadati tekst u datoteci (prvo pojavljivanje teksta). Tekst je uvijek jednolinijski.

* <b>findDat</b> <i>datoteka putanja</i> - pretražuje datoteku u zadatoj putanji i kao izlaz vraća lokaciju iste.

* <b>logout</b> - odjavljuje korisnika sa aplikacije, te briše dodjeljeni folder.
