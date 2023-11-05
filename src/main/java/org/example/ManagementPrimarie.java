package org.example;

import java.io.*;
import java.text.*;
import java.util.*;

public class ManagementPrimarie {
    static HashMap<String, Utilizator> utilizatori;
    static HashMap<String, Functionar> functionari;
    static Birou<Persoana> birouPersoane;
    static Birou<Angajat> birouAngajati;
    static Birou<Pensionar> birouPensionari;
    static Birou<Elev> birouElevi;
    static Birou<EntitateJuridica> birouEntJuridice;

    public static void print(String path, String text){
        try (FileWriter fw = new FileWriter("src/main/resources/output/" + path, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
             out.print(text);
        } catch (IOException e) { }
    }
    public static void main(String[] args) throws ParseException {
        String path = args[0];
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/resources/input/" + path)))) {
            String line;

            utilizatori = new HashMap<String, Utilizator>();
            functionari = new HashMap<String, Functionar>();
            birouPersoane = new Birou<Persoana>();
            birouPensionari = new Birou<Pensionar>();
            birouAngajati = new Birou<Angajat>();
            birouEntJuridice = new Birou<EntitateJuridica>();
            birouElevi = new Birou<Elev>();

            while ((line = br.readLine()) != null) {
                String[] data = line.split("; ");

                //adauga_utilizator; angajat; Irina Manole; SBP
                if(data[0].equals("adauga_utilizator")) {
                    Utilizator user = null;
                    if(data[1].equals("persoana")) { user = new Persoana(data[2]);}
                    else if(data[1].equals("angajat")) { user = new Angajat(data[2], data[3]);}
                    else if(data[1].equals("pensionar")) { user = new Pensionar(data[2]);}
                    else if(data[1].equals("elev")) { user = new Elev(data[2], data[3]);}
                    else if(data[1].equals("entitate juridica")) { user = new EntitateJuridica(data[2], data[3]);}
                    utilizatori.put(data[2], user);
                }

                // cerere_noua; Irina Manole; inlocuire buletin; 12-Dec-1999 23:00:00; 3
                if(data[0].equals("cerere_noua")) {
                    Utilizator user = utilizatori.get(data[1]);
                    Cerere cerere = new Cerere(user, data[2], data[3], data[4]);
                    try {
                        user.CreareCerere(cerere);
                    } catch(ExceptieCerere e) {
                        print(path, e.mesaj); //
                    }
                }

                // retrage_cerere; Irina Manole; 12-Dec-1999 23:00:00
                if(data[0].equals("retrage_cerere")) {
                    Date t = Cerere.sdf.parse(data[2]);
                    utilizatori.get(data[1]).RetrageCerere(t);
                }

                //afiseaza_cereri_in_asteptare; Irina Manole
                if(data[0].equals("afiseaza_cereri_in_asteptare")){
                    print(path,data[1] + " - cereri in asteptare:\n");
                    utilizatori.get(data[1]).printCereriAstepare(path);
                }

                //afiseaza_cereri; angajat
                if(data[0].equals("afiseaza_cereri")) {
                    Birou.AfisareCereri(path,data[1]);
                }

                //adauga_functionar; elev; Ion Popescu
                if(data[0].equals("adauga_functionar")) {
                    Birou.adaugaFunctionari(data[1], data[2]);
                }

                //rezolva_cerere; angajat; Alina Popescu
                if(data[0].equals("rezolva_cerere")) {
                    if(data[1].equals("persoana"))
                        Functionar.ProcesareCerere(new Persoana(), data[2]);
                    else if(data[1].equals("angajat"))
                        Functionar.ProcesareCerere(new Angajat(), data[2]);
                    else if(data[1].equals("pensionar"))
                        Functionar.ProcesareCerere(new Pensionar(), data[2]);
                    else if(data[1].equals("elev"))
                        Functionar.ProcesareCerere(new Elev(), data[2]);
                    else if(data[1].equals("entitate juridica"))
                        Functionar.ProcesareCerere(new EntitateJuridica(), data[2]);
                }

                //afiseaza_cereri_finalizate; George Georgescu
                if(data[0].equals("afiseaza_cereri_finalizate")) {
                    print(path, data[1] + " - cereri in finalizate:\n");
                    utilizatori.get(data[1]).printCereriSolutionate(path);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}