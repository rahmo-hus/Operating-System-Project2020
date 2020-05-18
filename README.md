# Osnovi Operativnih Sistema Projektni2020
 Elektrotehnički fakultet Banja Luka
Katedra za računarstvo i informatiku
Osnovi operativnih sistema
Projektni zadatak

Zadatak
Implementirati jednostavnu Shell aplikaciju (interfejs za interakciju korisnika sa operativnim sistemom) koja će olakšati korisniku interakciju sa postojećim operativnim sistemom. Aplikacija treba da radi na jednom operativnom sistemu, po izboru studenta (Windows, Linux, ...). Aplikacija treba da bude konzolna, tj. da korisniku ponudi alfanumerički interfejs u kojem korisnik unosi komande i dobija rezultate. Potrebno je podržati minimalno sljedeće komande:
1. login – prijava korisnika u aplikaciju. Korisnik unosi korisničko ime i lozinku. Aplikacija
treba da iz unaprijed kreirane baze korisničkih naloga (npr. tekstualna datoteka) provjeri
da li uneseno korisničko ime i lozinka postoje u bazi. Nakon uspješne prijave, za korisnika
se odredi tekući direktorijum (gdje se trenutno nalazi), na primjer:
/home/OOS/korisnik1. Sve naredne komande mogu da unose samo ulogovani
korisnici! Odjavu korisnika iz aplikacije realizovati pomoću komande logout.
2. where – prikazuje putanju do tekućeg direktorijuma u kojem se ulogovani korisnik nalazi.
Npr. /home/OOS/korisnik1
3. go putanja – mijenja tekući direktorijum korisnika. Novi tekući direktorijum je naveden
kao argument komande (putanja).
4. create [-d] putanja – na zadatoj putanji kreira novi direktorijum/datoteku. Ako se navede
parametar -d, kreira se direktorijum, u suprotnom – datoteka. Ako se navede samo naziv
(umjesto putanje), onda se u tekućem direktorijumu kreira novi direktorijum/datoteka.
Npr:
•
 create -d /home/dir1
 // kreira direktorijum dir1 u direktorijumu home
•
 create /home/dat1
 // kreira datoteku dat1 u direktorijumu home
•
 create -d dir1
 // kreira direktorijum dir1 u tekućem direktorijumu
•
 create dat1
 // kreira datoteku dat1 u tekućem direktorijumu
5. list [putanja] - izlistava sadržaj direktorijuma na zadatoj putanji (ili tekućeg direktorijuma
ako se ne navede putanja). Komanda formatirano ispisuje cijelo stablo direktorijuma i
datoteka, čiji je korijen zadati (ili tekući) direktorijum. Npr:
dir1
	dir11
		dat111
	dat11
	dat12
dir2
	dat21
6. print datoteka – ispisuje sadržaj (tekstualne) datoteke na konzolu. U slučaju navođenja
datoteke koja nije tekstualna, ispisuje se odgovarajuća poruka.
7. find “tekst” datoteka – pretražuje sadržaj datoteke u odnosu na zadati tekst. Ukoliko se
zadati tekst pronađe, komanda vraća rezultat u vidu broja linije datoteke u kojoj je
pronađen tekst (prvo pojavljivanje teksta). Tekst je uvijek jednolinijski.
8. findDat datoteka putanja – pretražuje stablo direktorijuma čiji je korijen naveden kao
putanja, i traži datoteku koja ima isti naziv kao prvi argument komande (datoteka). Npr:
findDat slika.png /home/OOS/korisnik1/slike
Zadatak je dozvoljeno realizovati u proizvoljnom programskom jeziku (bash, C, C++, Java, ...). Sve
detalje zadatka koji nisu precizno specifikovani realizovati na proizvoljan način. Voditi računa o
validaciji korisničkog unosa, tj. ispisivanju odgovarajućih poruka korisniku u slučaju nepravilnog
unosa komande. Studenti koji namjeravaju braniti projektni zadatak u određenom ispitnom roku,
dužni su postaviti arhivu sa rješenjem na moodle stranicu predmeta, najkasnije dan prije zakazane
odrbane. Odbrana se vrši u kabinetu predmetnog asistenta i organizuje se u svakom ispitnom roku.
Na odbranu je potrebno donijeti kompletan izvorni kod projektnog zadatka. Projektni zadatak
vrijedi do objavljivanja sljedećeg projektnog zadatka.
