package org.example;

import java.text.*;
import java.util.*;

public class Cerere {
    public String text;
    public int prioritate;
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy' 'HH:mm:ss");
    public Date data;
    public tipCerere tip;
    public Utilizator solicitant;

    public enum tipCerere {
       BULETIN("inlocuire buletin"),
       VENIT("inregistrare venit salarial"),
       SOFER("inlocuire carnet de sofer"),
       ELEV("inlocuire carnet de elev"),
       ACT("creare act constitutiv"),
       AUTORIZATIE("reinnoire autorizatie"),
       PENSIE("inregistrare cupoane de pensie");
        final String def;
        tipCerere (String nume) {
            this.def = nume;
        }
        public boolean equalsCerere(String s) {
            return def.equals(s);
        }
        public String toString() {
            return this.def;
        } // din Enum in String
        public static tipCerere getTipCerere(String def) { // din String in enum
            for(tipCerere x : tipCerere.values()) {
                if(x.def.equals(def)) return x;
            }
            return null;
        }
    }
    public Cerere(Utilizator u,String tip, String timp, String prioritate) throws ParseException {
        this.tip = tipCerere.getTipCerere(tip);
        this.prioritate = Integer.parseInt(prioritate);
        this.solicitant = u;
        this.data = sdf.parse(timp);
    }

    public String toString() {
        return sdf.format(data) + " - " + text;
    }

    // clasa folosita pentru TreeSet : cererile in asteptare si solutionate
    static class ComparaData implements Comparator<Cerere> {

        public int compare(Cerere a, Cerere b) {
            return a.data.compareTo(b.data);
        }

    }

    // clasa folosita pentru TreeSet : cererile din birou
    static class ComparaPrioritate implements Comparator<Cerere> {
        public int compare(Cerere a, Cerere b) {
            if(a.prioritate < b.prioritate)
                return 1;
            else if(a.prioritate > b.prioritate)
                return -1;
            else
                return a.data.compareTo(b.data);
        }
    }
}
