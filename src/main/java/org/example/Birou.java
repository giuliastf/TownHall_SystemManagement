package org.example;

import java.util.*;

public class Birou <cetateni extends Utilizator> {
    public TreeSet<Cerere> cereri;
    public HashMap<String, Functionar > functionari = new HashMap<String, Functionar>();

    Birou() {
        cereri = new TreeSet<Cerere>(new Cerere.ComparaPrioritate()); // cererile sunt stocate in arbore dupa prioritate
        functionari = new HashMap<String, Functionar>();
    }

    public static void AfisareCereri(String path, String u) {
        if(u.equals("persoana")) {
            ManagementPrimarie.print(path,"persoana - cereri in birou:\n");
            for(Cerere c: ManagementPrimarie.birouPersoane.cereri) // accesez biroul corespunzator tipului de utilizator
                ManagementPrimarie.print(path, c.prioritate + " - " + c.toString() + '\n');
        }
        else if(u.equals("angajat")) {
            ManagementPrimarie.print(path,"angajat - cereri in birou:\n");
            for(Cerere c: ManagementPrimarie.birouAngajati.cereri)
                ManagementPrimarie.print(path, c.prioritate + " - " + c.toString() + '\n');
        }
        else if(u.equals("pensionar")) {
            ManagementPrimarie.print(path, "pensionar - cereri in birou:\n");
            for(Cerere c: ManagementPrimarie.birouPensionari.cereri)
                ManagementPrimarie.print(path, c.prioritate + " - " + c.toString() + '\n');
        }
        else if(u.equals("elev")) {
            ManagementPrimarie.print(path, "elev - cereri in birou:\n");
            for(Cerere c: ManagementPrimarie.birouElevi.cereri)
                ManagementPrimarie.print(path, c.prioritate + " - " + c.toString() + '\n');
        }
        else if(u.equals("entitate juridica")) {
            ManagementPrimarie.print(path,"entitate juridica - cereri in birou:\n");
            for(Cerere c: ManagementPrimarie.birouEntJuridice.cereri)
                ManagementPrimarie.print(path, c.prioritate + " - " + c.toString() + '\n');
        }
    }

    public static <cetateni extends Utilizator> void AdaugaCerere(Cerere c, cetateni u) {
        if(u instanceof Persoana) {  ManagementPrimarie.birouPersoane.cereri.add(c);}
        else if(u instanceof Angajat) { ManagementPrimarie.birouAngajati.cereri.add(c);}
        else if(u instanceof Pensionar) { ManagementPrimarie.birouPensionari.cereri.add(c);}
        else if(u instanceof Elev) {  ManagementPrimarie.birouElevi.cereri.add(c);}
        else if(u instanceof EntitateJuridica) { ManagementPrimarie.birouEntJuridice.cereri.add(c);}
    }

    public static <cetateni extends Utilizator> void StergeCerere(Cerere c, cetateni u) {
        if(u instanceof Persoana) { ManagementPrimarie.birouPersoane.cereri.remove(c);}
        else if(u instanceof Angajat) { ManagementPrimarie.birouAngajati.cereri.remove(c);}
        else if(u instanceof Pensionar) { ManagementPrimarie.birouPensionari.cereri.remove(c);}
        else if(u instanceof Elev) { ManagementPrimarie.birouElevi.cereri.remove(c);}
        else if(u instanceof EntitateJuridica) { ManagementPrimarie.birouEntJuridice.cereri.remove(c);}
    }

    // scot din TreeSet de cereri (deja sortat) prima cerere
    public Cerere RezolvaCerere(){
        Cerere c = cereri.first();
        cereri.remove(c);
        return c;
    }
    // elev, nume
    public static void adaugaFunctionari(String u, String f){

        Functionar func = new Functionar(f);
        if(u.equals("persoana")) { ManagementPrimarie.birouPersoane.functionari.put(f,func);}
        else if(u.equals("angajat")) { ManagementPrimarie.birouAngajati.functionari.put(f,func);}
        else if(u.equals("pensionar")) { ManagementPrimarie.birouPensionari.functionari.put(f,func);}
        else if(u.equals("elev")) { ManagementPrimarie.birouElevi.functionari.put(f,func);}
        else if(u.equals("entitate juridica")) { ManagementPrimarie.birouEntJuridice.functionari.put(f,func);}
        ManagementPrimarie.functionari.put(f, func);
    }
}
