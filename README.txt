TEMA 2 POO
IMBREA GIULIA-STEFANIA
321CB
github: giuliastf
----------------
Birou.java
TreeSet<Cerere> cereri = am ales ca cererile sa fie stocate ca TreeSet pentru a le putea tine ordonate.
HashMap<String, Functionar > functionari = ca la utilizatori am ales ca functionarii sa fie retinuti intr un HashMap deoarece
este usor sa-i caut dupa cheia = nume

void AfisareCereri(String path, String u) = afiseaza cererile din biroul corespunzator tipul de Utilizator
void AdaugaCerere(Cerere c, cetateni u) = adaug (.add) noua cerere in TreeSet de cereri corespunzator(verific cu instanceof tipul de utilizator)
void StergeCerere(Cerere c, cetateni u) = ca la adaugare doar ca pun (.remove)
Cerere RezolvaCerere() = scot din TreeSet de cereri(deja sortat) prima cerere
void adaugaFunctionari(String u, String f) = asemanator cu AdaugaCerere
-----------------
Cerere.java
String toString() = afiseaza cererea in formatul cerut ( data - text_cerere )
class ComparaData si class ComparaPrioritate sunt folosite odata cu declararea cererilor (TreeSet) pt a stabili criteriul de ordonare a elementelor din TreeSet
-----------------
Functionar.java
void ProcesareCerere (cetateni u, String func) = in functie de tipul de user, sunt apelate metodele
    get(func) => obtin functionarul dupa nume
    RezolvaCerere din Birou.java
    Daca ultima metoda s-a terminat cu succes cererea obtinuta din RezolvaCerere este scoasa din cererile in asteptare si
    este mutata in cele solutionate/finalizate  (.remove si .add)
    La final, este creat fisierul pt functionar asa cum este specificat in cerinta (DACA NU EXISTA DEJA), unde va fi adaugat pe
    urmatoarea linia textul pt cererea rezolvata
------------------
Utilizator.java
void CreareCerere(Cerere c) = apelez metoda TextCerere pt a obtine textul ei
                              adaug metoda in TreeSet cu cererile in asteptare
                              apelez AdaugaCerere pt a adauga cererea si in lista cu cererile a biroului specific
void RetrageCerere(Date data) = caut in cererile aflate in asteptare cea care are aceeasi data.
                                daca am gasit-o => remove din cererile de asteptare
                                apelez Birou.StergeCerere pt a sterge cererea si din cererile biroului in care se afla
String TextCerere(String tip) = realizeaza textul pt cerere in functie de tipul utilizatorului
void printCereriAstepare(String path) = cu un for parcurg cererile in asteptate si le afisez folosind toString
void printCereriSolutionate(String path)

-----------------
ManagementPrimarie.java
void print(String path, String text) = metoda folosita pt a scrie text in fisierul de output
pt scriere/citire in/din fisiere am folositcod din tema 1.
aici in functie de primul cuvant din linie(data[0]) apelez metode specifice in vederea rezolvarii cerintelor

--------------
EXPLICATII SUPLIMENTARE:
cum am ales tipul de colectii?
In cazul utilizatorilor am preferat sa folosesc HashMap deoarece mi a fost usor sa i identific dupa cheia lor, adica numele
In cazul cererilor am folosit TreeSet uri deoarece mi a fost usor sa sortez elementele dupa cerinta folosindu-ma de comparator