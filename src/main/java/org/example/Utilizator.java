package org.example;

import java.util.*;

public abstract class Utilizator {
    public String nume;
    public TreeSet<Cerere> asteptare;
    public TreeSet<Cerere> solutionate;
    protected Utilizator() {
    }
    protected Utilizator(String nume) {
        this.nume = nume;
        this.asteptare = new TreeSet<Cerere>(new Cerere.ComparaData());
        this.solutionate = new TreeSet<Cerere>(new Cerere.ComparaData());
    }
    public abstract String TextCerere(String tip) throws ExceptieCerere;
    // cerere noua in asteptare si in cereri (Birou)
    public void CreareCerere(Cerere c) throws ExceptieCerere {
        c.text = TextCerere(c.tip.toString());
        asteptare.add(c);
        Birou.AdaugaCerere(c, c.solicitant);
    }
    // sterge cerere din asteptare si din cereri (Birou)
    public void RetrageCerere(Date data) {
        Cerere aux = null;
        for(Cerere c : asteptare)
            if(c.data.compareTo(data) == 0) {
                aux = c;
                asteptare.remove(c);
                break;
            }
        Birou.StergeCerere(aux, aux.solicitant);
    }

    public void printCereriAstepare(String path) {
        for(Cerere c : asteptare)
            ManagementPrimarie.print(path, c.toString() + '\n');
    }
    public void printCereriSolutionate(String path) {
        for(Cerere c : solutionate)
            ManagementPrimarie.print(path, c.toString() + '\n');
    }
}

class Persoana extends Utilizator {
    public Persoana(){}
    public Persoana(String nume) {
        super(nume);
    }
    public String TextCerere(String tip) throws ExceptieCerere{ // (Cerere.tip tip)
        if(!(Cerere.tipCerere.BULETIN.equalsCerere(tip) || Cerere.tipCerere.SOFER.equalsCerere(tip)))
            throw new ExceptieCerere("Utilizatorul de tip persoana nu poate inainta o cerere de tip " + tip +"\n");
        else
            return "Subsemnatul " + nume + ", va rog sa-mi aprobati urmatoarea solicitare: " + tip;
    }
}
class Angajat extends Utilizator {
    public Angajat(){}
    private String companie;
    public Angajat(String nume, String companie) {
        super(nume);
        this.companie = companie;
    }
    public String getCompanie () {
        return companie;
    }
    public void setCompanie(String companie) {
        this.companie = companie;
    }
    public String TextCerere(String tip) throws ExceptieCerere { // (Cerere.tip tip)
        if (!(Cerere.tipCerere.BULETIN.equalsCerere(tip) || Cerere.tipCerere.SOFER.equalsCerere(tip) || Cerere.tipCerere.VENIT.equalsCerere(tip)))
            throw new ExceptieCerere("Utilizatorul de tip angajat nu poate inainta o cerere de tip " + tip+"\n");
        else
            return "Subsemnatul " + nume + ", angajat la compania " + this.getCompanie() + ", va rog sa-mi aprobati urmatoarea solicitare: " + tip;
    }
}
class Pensionar extends Utilizator {
    public Pensionar(){}
    public Pensionar(String nume) {
        super(nume);
    }
    public String TextCerere(String tip) throws ExceptieCerere {
        if(!(Cerere.tipCerere.BULETIN.equalsCerere(tip) || Cerere.tipCerere.SOFER.equalsCerere(tip) || Cerere.tipCerere.PENSIE.equalsCerere(tip)))
            throw new ExceptieCerere("Utilizatorul de tip pensionar nu poate inainta o cerere de tip " + tip+"\n");
        else
            return "Subsemnatul " + nume + ", va rog sa-mi aprobati urmatoarea solicitare: " + tip;
    }
}
class Elev extends Utilizator {
    public Elev(){}
    private String scoala;
    public Elev(String nume, String scoala) {
        super(nume);
        this.scoala = scoala;
    }
    public String getScoala () {
        return scoala;
    }
    public void setScoala(String scoala) {
        this.scoala = scoala;
    }
    public String TextCerere(String tip) throws ExceptieCerere{ // (Cerere.tip tip)
        if(!(Cerere.tipCerere.BULETIN.equalsCerere(tip) || Cerere.tipCerere.ELEV.equalsCerere(tip)))
            throw new ExceptieCerere("Utilizatorul de tip elev nu poate inainta o cerere de tip " + tip+"\n");
        else
            return "Subsemnatul " + nume + ", elev la scoala " + this.getScoala() + ", va rog sa-mi aprobati urmatoarea solicitare: " + tip;
    }
}

class EntitateJuridica extends Utilizator {
    public EntitateJuridica(){}
    private String reprezentant;
    public EntitateJuridica(String nume, String reprezentant) {
        super(nume);
        this.reprezentant = reprezentant;
    }
    public String getReprezentant () {
        return reprezentant;
    }
    public void setReprezentant(String reprezentant) {
        this.reprezentant = reprezentant;
    }
    public String TextCerere(String tip) throws ExceptieCerere{ // (Cerere.tip tip)
        if(!(Cerere.tipCerere.ACT.equalsCerere(tip) || Cerere.tipCerere.AUTORIZATIE.equalsCerere(tip)))
            throw new ExceptieCerere("Utilizatorul de tip entitate juridica nu poate inainta o cerere de tip " + tip+"\n");
        else
            return "Subsemnatul " + this.getReprezentant() + ", reprezentant legal al companiei " + nume + ", va rog sa-mi aprobati urmatoarea solicitare: " + tip;
    }
}
