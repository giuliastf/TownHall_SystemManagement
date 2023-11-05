package org.example;

import java.io.*;
public class Functionar <cetateni extends Utilizator> {
    static String nume;
    public Functionar (String nume){
        this.nume = nume;
    }
    public static <cetateni extends Utilizator> void ProcesareCerere (cetateni u, String func) {
        Functionar f = null;
        Cerere c = null;
        if (u instanceof Persoana) {
            f = ManagementPrimarie.birouPersoane.functionari.get(func); // obtin obiectul Functionar
            c =  ManagementPrimarie.birouPersoane.RezolvaCerere(); // din cererile din BirouPersoane este scoasa prima(cea mai importanta)
        } else if (u instanceof Angajat) {
            f = ManagementPrimarie.birouAngajati.functionari.get(func);
            c =  ManagementPrimarie.birouAngajati.RezolvaCerere();
        } else if (u instanceof Pensionar) {
            f = ManagementPrimarie.birouPensionari.functionari.get(func);
            c =  ManagementPrimarie.birouPensionari.RezolvaCerere();
        } else if (u instanceof Elev) {
            f = ManagementPrimarie.birouElevi.functionari.get(func);
            c =  ManagementPrimarie.birouElevi.RezolvaCerere();
        } else if (u instanceof EntitateJuridica) {
            f = ManagementPrimarie.birouEntJuridice.functionari.get(func);
            c =  ManagementPrimarie.birouEntJuridice.RezolvaCerere();
        }

        c.solicitant.asteptare.remove(c);
        c.solicitant.solutionate.add(c);


        try (FileWriter fw = new FileWriter("src/main/resources/output/" + "functionar_" + func + ".txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
             out.print(Cerere.sdf.format(c.data) + " - " + c.solicitant.nume + "\n");
        } catch (IOException e) { }
    }
}
